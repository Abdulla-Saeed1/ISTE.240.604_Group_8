/* Author: Abdulla Almarri (ID: 744000213) */
/* users.js — JavaScript for the Users management page */

const API = '/api/users';

function showToast(msg, type = 'success') {
    const el = document.getElementById('mainToast');
    el.className = `toast align-items-center border-0 text-white bg-${type}`;
    document.getElementById('toastMsg').textContent = msg;
    bootstrap.Toast.getOrCreateInstance(el, { delay: 3000 }).show();
}

function setOverlay(show) {
    document.getElementById('loadingOverlay').classList.toggle('d-none', !show);
}

function filterTable(q) {
    const term = q.toLowerCase();
    document.querySelectorAll('#tableBody tr').forEach(r => {
        r.style.display = r.textContent.toLowerCase().includes(term) ? '' : 'none';
    });
}

async function loadData() {
    const s = document.getElementById('searchInput');
    if (s) s.value = '';
    document.getElementById('tableBody').innerHTML =
        `<tr><td colspan="6" class="text-center py-4"><div class="spinner-border spinner-border-sm text-primary me-2"></div>Loading...</td></tr>`;
    try {
        const res = await fetch(API);
        const users = await res.json();
        const tbody = document.getElementById('tableBody');
        if (!users.length) {
            tbody.innerHTML = `<tr><td colspan="6" class="text-center text-muted py-4">No users found.</td></tr>`;
            return;
        }
        tbody.innerHTML = users.map(u => `
            <tr>
                <td><span class="badge bg-secondary">${u.userId}</span></td>
                <td class="fw-semibold">${u.name}</td>
                <td>${u.email}</td>
                <td>${u.phoneNumber}</td>
                <td><span class="badge ${u.membershipType === 'Premium' ? 'bg-warning text-dark' : 'bg-info text-dark'}">${u.membershipType}</span></td>
                <td>
                    <button class="btn btn-sm btn-outline-warning btn-action me-1" onclick="openEdit(${u.userId},'${u.name}','${u.email}',${u.phoneNumber},'${u.password}','${u.membershipType}')">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button class="btn btn-sm btn-outline-danger btn-action" onclick="deleteRecord(${u.userId})">
                        <i class="bi bi-trash"></i>
                    </button>
                </td>
            </tr>`).join('');
    } catch {
        showToast('Failed to load users.', 'danger');
    }
}

document.getElementById('addForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const btn = document.getElementById('addBtn');
    btn.disabled = true;
    btn.innerHTML = '<span class="spinner-border spinner-border-sm me-2"></span>Saving...';
    setOverlay(true);
    try {
        const res = await fetch(API, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                userId: parseInt(document.getElementById('userId').value),
                name: document.getElementById('name').value,
                email: document.getElementById('email').value,
                phoneNumber: parseInt(document.getElementById('phoneNumber').value),
                password: document.getElementById('password').value,
                membershipType: document.getElementById('membershipType').value
            })
        });
        if (res.ok) {
            showToast('User added successfully!');
            document.getElementById('addForm').reset();
            loadData();
        } else {
            showToast('Failed to add user. Check the ID is unique.', 'danger');
        }
    } catch { showToast('Network error.', 'danger'); }
    finally {
        btn.disabled = false;
        btn.innerHTML = '<i class="bi bi-plus-circle me-1"></i>Add User';
        setOverlay(false);
    }
});

function openEdit(id, name, email, phone, password, membership) {
    document.getElementById('editId').value = id;
    document.getElementById('editName').value = name;
    document.getElementById('editEmail').value = email;
    document.getElementById('editPhone').value = phone;
    document.getElementById('editPassword').value = password;
    document.getElementById('editMembership').value = membership;
    new bootstrap.Modal(document.getElementById('editModal')).show();
}

document.getElementById('saveEditBtn').addEventListener('click', async () => {
    const id = document.getElementById('editId').value;
    const btn = document.getElementById('saveEditBtn');
    btn.disabled = true;
    btn.innerHTML = '<span class="spinner-border spinner-border-sm me-2"></span>Saving...';
    setOverlay(true);
    try {
        const res = await fetch(`${API}/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                userId: parseInt(id),
                name: document.getElementById('editName').value,
                email: document.getElementById('editEmail').value,
                phoneNumber: parseInt(document.getElementById('editPhone').value),
                password: document.getElementById('editPassword').value,
                membershipType: document.getElementById('editMembership').value
            })
        });
        if (res.ok) {
            bootstrap.Modal.getInstance(document.getElementById('editModal')).hide();
            showToast('User updated successfully!');
            loadData();
        } else {
            showToast('Update failed.', 'danger');
        }
    } catch { showToast('Network error.', 'danger'); }
    finally {
        btn.disabled = false;
        btn.innerHTML = '<i class="bi bi-check-circle me-1"></i>Save Changes';
        setOverlay(false);
    }
});

async function deleteRecord(id) {
    if (!confirm(`Delete user #${id}? This cannot be undone.`)) return;
    setOverlay(true);
    try {
        const res = await fetch(`${API}/${id}`, { method: 'DELETE' });
        if (res.ok) { showToast('User deleted.', 'secondary'); loadData(); }
        else showToast('Delete failed.', 'danger');
    } catch { showToast('Network error.', 'danger'); }
    finally { setOverlay(false); }
}

loadData();
