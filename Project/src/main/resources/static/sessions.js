/* Author: Ujjwal Jain (ID: 746007518) */
/* sessions.js — JavaScript for the Fitness Sessions management page */

const API = '/api/sessions';

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
        `<tr><td colspan="9" class="text-center py-4"><div class="spinner-border spinner-border-sm text-warning me-2"></div>Loading...</td></tr>`;
    try {
        const res = await fetch(API);
        const items = await res.json();
        const tbody = document.getElementById('tableBody');
        if (!items.length) { tbody.innerHTML = `<tr><td colspan="9" class="text-center text-muted py-4">No sessions found.</td></tr>`; return; }
        tbody.innerHTML = items.map(s => `
            <tr>
                <td><span class="badge bg-secondary">${s.sessionId}</span></td>
                <td class="fw-semibold">${s.sessionName}</td>
                <td><span class="badge bg-info text-dark">#${s.trainerId}</span></td>
                <td>${s.date}</td>
                <td>${s.time}</td>
                <td>${s.duration} min</td>
                <td>${s.capacity}</td>
                <td>${s.location}</td>
                <td>
                    <button class="btn btn-sm btn-outline-warning btn-action me-1" onclick="openEdit(${s.sessionId},'${s.sessionName}',${s.trainerId},'${s.date}','${s.time}',${s.duration},${s.capacity},'${s.location}')">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button class="btn btn-sm btn-outline-danger btn-action" onclick="deleteRecord(${s.sessionId})">
                        <i class="bi bi-trash"></i>
                    </button>
                </td>
            </tr>`).join('');
    } catch { showToast('Failed to load sessions.', 'danger'); }
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
                sessionId: parseInt(document.getElementById('sessionId').value),
                sessionName: document.getElementById('sessionName').value,
                trainerId: parseInt(document.getElementById('trainerId').value),
                date: document.getElementById('date').value,
                time: document.getElementById('time').value,
                duration: parseInt(document.getElementById('duration').value),
                capacity: parseInt(document.getElementById('capacity').value),
                location: document.getElementById('location').value
            })
        });
        if (res.ok) { showToast('Session added successfully!'); document.getElementById('addForm').reset(); loadData(); }
        else showToast('Failed to add session. Check the ID is unique.', 'danger');
    } catch { showToast('Network error.', 'danger'); }
    finally { btn.disabled = false; btn.innerHTML = '<i class="bi bi-plus-circle me-1"></i>Add Session'; setOverlay(false); }
});

function openEdit(id, name, trainer, date, time, duration, capacity, location) {
    document.getElementById('editId').value = id;
    document.getElementById('editName').value = name;
    document.getElementById('editTrainer').value = trainer;
    document.getElementById('editDate').value = date;
    document.getElementById('editTime').value = time;
    document.getElementById('editDuration').value = duration;
    document.getElementById('editCapacity').value = capacity;
    document.getElementById('editLocation').value = location;
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
                sessionId: parseInt(id),
                sessionName: document.getElementById('editName').value,
                trainerId: parseInt(document.getElementById('editTrainer').value),
                date: document.getElementById('editDate').value,
                time: document.getElementById('editTime').value,
                duration: parseInt(document.getElementById('editDuration').value),
                capacity: parseInt(document.getElementById('editCapacity').value),
                location: document.getElementById('editLocation').value
            })
        });
        if (res.ok) { bootstrap.Modal.getInstance(document.getElementById('editModal')).hide(); showToast('Session updated!'); loadData(); }
        else showToast('Update failed.', 'danger');
    } catch { showToast('Network error.', 'danger'); }
    finally { btn.disabled = false; btn.innerHTML = '<i class="bi bi-check-circle me-1"></i>Save Changes'; setOverlay(false); }
});

async function deleteRecord(id) {
    if (!confirm(`Delete session #${id}?`)) return;
    setOverlay(true);
    try {
        const res = await fetch(`${API}/${id}`, { method: 'DELETE' });
        if (res.ok) { showToast('Session deleted.', 'secondary'); loadData(); }
        else showToast('Delete failed.', 'danger');
    } catch { showToast('Network error.', 'danger'); }
    finally { setOverlay(false); }
}

loadData();