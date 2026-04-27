/* Author: Hamad Bin Dasmal (ID: 743009877) */
/* bookings.js — JavaScript for the Bookings management page */

const API = '/api/bookings';

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
        `<tr><td colspan="6" class="text-center py-4"><div class="spinner-border spinner-border-sm text-danger me-2"></div>Loading...</td></tr>`;
    try {
        const res = await fetch(API);
        const items = await res.json();
        const tbody = document.getElementById('tableBody');
        if (!items.length) { tbody.innerHTML = `<tr><td colspan="6" class="text-center text-muted py-4">No bookings found.</td></tr>`; return; }
        tbody.innerHTML = items.map(b => `
            <tr>
                <td><span class="badge bg-secondary">${b.bookingId}</span></td>
                <td><span class="badge bg-primary">#${b.userId}</span></td>
                <td><span class="badge bg-info text-dark">#${b.sessionId}</span></td>
                <td>${b.bookingDate}</td>
                <td><span class="badge ${b.status === 'Confirmed' ? 'bg-success' : 'bg-warning text-dark'}">${b.status}</span></td>
                <td>
                    <button class="btn btn-sm btn-outline-warning btn-action me-1" onclick="openEdit(${b.bookingId},${b.userId},${b.sessionId},'${b.bookingDate}','${b.status}')">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button class="btn btn-sm btn-outline-danger btn-action" onclick="deleteRecord(${b.bookingId})">
                        <i class="bi bi-trash"></i>
                    </button>
                </td>
            </tr>`).join('');
    } catch { showToast('Failed to load bookings.', 'danger'); }
}

document.getElementById('addForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const btn = document.getElementById('addBtn');
    btn.disabled = true; btn.innerHTML = '<span class="spinner-border spinner-border-sm me-2"></span>Saving...';
    setOverlay(true);
    try {
        const res = await fetch(API, {
            method: 'POST', headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                bookingId: parseInt(document.getElementById('bookingId').value),
                userId: parseInt(document.getElementById('userId').value),
                sessionId: parseInt(document.getElementById('sessionId').value),
                bookingDate: document.getElementById('bookingDate').value,
                status: document.getElementById('status').value
            })
        });
        if (res.ok) { showToast('Booking added successfully!'); document.getElementById('addForm').reset(); loadData(); }
        else showToast('Failed to add booking. Check the ID is unique.', 'danger');
    } catch { showToast('Network error.', 'danger'); }
    finally { btn.disabled = false; btn.innerHTML = '<i class="bi bi-plus-circle me-1"></i>Add Booking'; setOverlay(false); }
});

function openEdit(id, userId, sessionId, date, status) {
    document.getElementById('editId').value = id;
    document.getElementById('editUserId').value = userId;
    document.getElementById('editSessionId').value = sessionId;
    document.getElementById('editDate').value = date;
    document.getElementById('editStatus').value = status;
    new bootstrap.Modal(document.getElementById('editModal')).show();
}

document.getElementById('saveEditBtn').addEventListener('click', async () => {
    const id = document.getElementById('editId').value;
    const btn = document.getElementById('saveEditBtn');
    btn.disabled = true; btn.innerHTML = '<span class="spinner-border spinner-border-sm me-2"></span>Saving...';
    setOverlay(true);
    try {
        const res = await fetch(`${API}/${id}`, {
            method: 'PUT', headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                bookingId: parseInt(id),
                userId: parseInt(document.getElementById('editUserId').value),
                sessionId: parseInt(document.getElementById('editSessionId').value),
                bookingDate: document.getElementById('editDate').value,
                status: document.getElementById('editStatus').value
            })
        });
        if (res.ok) { bootstrap.Modal.getInstance(document.getElementById('editModal')).hide(); showToast('Booking updated!'); loadData(); }
        else showToast('Update failed.', 'danger');
    } catch { showToast('Network error.', 'danger'); }
    finally { btn.disabled = false; btn.innerHTML = '<i class="bi bi-check-circle me-1"></i>Save Changes'; setOverlay(false); }
});

async function deleteRecord(id) {
    if (!confirm(`Delete booking #${id}?`)) return;
    setOverlay(true);
    try {
        const res = await fetch(`${API}/${id}`, { method: 'DELETE' });
        if (res.ok) { showToast('Booking deleted.', 'secondary'); loadData(); }
        else showToast('Delete failed.', 'danger');
    } catch { showToast('Network error.', 'danger'); }
    finally { setOverlay(false); }
}

loadData();