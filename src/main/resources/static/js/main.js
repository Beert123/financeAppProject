const list = document.getElementById('transaction-list');
const form= document.getElementById('transaction-form');
const formCategory  = document.getElementById('category-form');
const listCategory = document.getElementById("category-list")

function loadTransactions() {
    fetch('/trans')
        .then(res => res.json())
        .then(data => {
            list.innerHTML = '';
            data.forEach(t => {
                const item = document.createElement('li');
                item.textContent = `${t.date} - ${t.amount} DKK - ${t.description}`;
                list.appendChild(item);
            });
        });
}
function loadCategories(){
    fetch('/categories')
        .then(res => res.json())
        .then(data => {
            listCategory.innerHTML = '';
            data.forEach(t => {
                const item = document.createElement('li');
                item.textContent = `${t.name} - ${t.type}`;
                listCategory.appendChild(item);
            });
        });
}

form.addEventListener('submit', e => {
    e.preventDefault();
    const formData = new FormData(form);
    const jsonData = Object.fromEntries(formData);
    jsonData.amount = parseFloat(jsonData.amount);

    fetch('/transPost', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(jsonData)
    }).then(() => {
        form.reset();
        loadTransactions();
    });
});
formCategory.addEventListener('submit', e => {
    e.preventDefault();
    const formData = new FormData(formCategory);
    const jsonData = Object.fromEntries(formData);

    fetch('/categoriesPost', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(jsonData)
    }).then(() => {
        formCategory.reset();
        loadCategories();
    });
})
loadCategories();
loadTransactions();
