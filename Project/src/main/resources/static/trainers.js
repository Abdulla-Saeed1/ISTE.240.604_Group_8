/* Author: Khalifa Alhammadi (ID: 399001487) */
/* trainers.js — JavaScript for the Trainers management page */

const API = '/api/trainers';

function showToast(msg, type = 'success') {
    const el = document.getElementById('mainToast');
    el.className = `toast align-items-center border-0 text-white bg-${type}`;
    document.getElementById('toastMsg').textContent = msg;
    bootstrap.Toast.getOrCreateInstance(el, { delay: 3000 }).show();
}

function setOverlay(show) {
    document.getElementById('loadingOverlay').classList.toggle('d-none', !show);
}

function stars(r) {
    const full = Math.floor(r), half = r % 1 >= 0.5;
    let s = '';
    for (let i = 0; i < full; i++) s += '<i class="bi bi-star-fill text-warning"></i>';
    if (half) s += '<i class="bi bi-star-half text-warning"></i>';
    return s + ` <small class="text-muted">(${r})</small>`;
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
        `<tr><td colspan="7" class="text-center py-4"><div class="spinner-border spinner-border-sm text-success me-2"></div>Loading...</td></tr>`;
    try {
        const res = await fetch(API);
        const items = await res.json();
        const tbody = document.getElementById('tableBody');
        if (!items.length) { tbody.innerHTML = `<tr><td colspan="7" class="text-center text-muted py-4">No trainers found.</td></tr>`; return; }
        tbody.innerHTML = items.map(t => `
            <tr>
                <td><span class="badge bg-secondary">${t.trainerId}</span></td>
                <td class="fw-semibold">${t.name}</td>
                <td><span class="badge bg-success">${t.specialization}</span></td>
                <td>${t.experienceYears} yrs</td>
                <td>${t.availabilitySchedule}</td>
                <td>${stars(t.rating)}</td>
                <td>
                    <button class="btn btn-sm btn-outline-warning btn-action me-1" onclick="openEdit(${t.trainerId},'${t.name}','${t.specialization}',${t.experienceYears},'${t.availabilitySchedule}',${t.rating})">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button class="btn btn-sm btn-outline-danger btn-action" onclick="deleteRecord(${t.trainerId})">
                        <i class="bi bi-trash"></i>
                    </button>
                </td>
            </tr>`).join('');
    } catch { showToast('Failed to load trainers.', 'danger'); }
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
                trainerId: parseInt(document.getElementById('trainerId').value),
                name: document.getElementById('name').value,
                specialization: document.getElementById('specialization').value,
                experienceYears: parseInt(document.getElementById('experienceYears').value),
                availabilitySchedule: document.getElementById('availabilitySchedule').value,
                rating: parseFloat(document.getElementById('rating').value)
            })
        });
        if (res.ok) { showToast('Trainer added successfully!'); document.getElementById('addForm').reset(); loadData(); }
        else showToast('Failed to add trainer. Check the ID is unique.', 'danger');
    } catch { showToast('Network error.', 'danger'); }
    finally { btn.disabled = false; btn.innerHTML = '<i class="bi bi-plus-circle me-1"></i>Add Trainer'; setOverlay(false); }
});

function openEdit(id, name, spec, exp, avail, rating) {
    document.getElementById('editId').value = id;
    document.getElementById('editName').value = name;
    document.getElementById('editSpec').value = spec;
    document.getElementById('editExp').value = exp;
    document.getElementById('editAvail').value = avail;
    document.getElementById('editRating').value = rating;
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
                trainerId: parseInt(id),
                name: document.getElementById('editName').value,
                specialization: document.getElementById('editSpec').value,
                experienceYears: parseInt(document.getElementById('editExp').value),
                availabilitySchedule: document.getElementById('editAvail').value,
                rating: parseFloat(document.getElementById('editRating').value)
            })
        });
        if (res.ok) { bootstrap.Modal.getInstance(document.getElementById('editModal')).hide(); showToast('Trainer updated!'); loadData(); }
        else showToast('Update failed.', 'danger');
    } catch { showToast('Network error.', 'danger'); }
    finally { btn.disabled = false; btn.innerHTML = '<i class="bi bi-check-circle me-1"></i>Save Changes'; setOverlay(false); }
});

async function deleteRecord(id) {
    if (!confirm(`Delete trainer #${id}?`)) return;
    setOverlay(true);
    try {
        const res = await fetch(`${API}/${id}`, { method: 'DELETE' });
        if (res.ok) { showToast('Trainer deleted.', 'secondary'); loadData(); }
        else showToast('Delete failed.', 'danger');
    } catch { showToast('Network error.', 'danger'); }
    finally { setOverlay(false); }
}

loadData();