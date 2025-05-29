const listTransaction = document.getElementById('transaction-list');
const formTransaction= document.getElementById('transaction-form');
const formCategory  = document.getElementById('category-form');
const listCategory = document.getElementById("category-list")

function loadTransactions() {
    fetch('/trans')
        .then(res => res.json())
        .then(data => {
            listTransaction.innerHTML = '';
            data.forEach(t => {
                const item = document.createElement('li');
                item.textContent = `${t.date} - ${t.amount} DKK - ${t.description}`;
                item.onclick = () =>{
                    if (confirm(`Delete transaction '${t.name}'?`)) {
                        fetch(`/trans/${t.id}`, {
                            method: 'DELETE'
                        }).then(() => {
                            loadCategories();
                            loadTransactions();
                        });
                    }
                }
                listTransaction.appendChild(item);
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
                item.onclick = () =>{
                    if (confirm(`Delete category '${t.name}'?`)) {
                        fetch(`/categories/${t.id}`, {
                            method: 'DELETE'
                        }).then(() => {
                            loadCategories();
                            loadTransactions();
                        });
                    }
                }
                listCategory.appendChild(item);
            });
        });
}

formTransaction.addEventListener('submit', e => {
    e.preventDefault();
    const formData = new FormData(formTransaction);
    const jsonData = Object.fromEntries(formData);
    jsonData.amount = parseFloat(jsonData.amount);

    fetch('/trans', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(jsonData)
    }).then(() => {
        formTransaction.reset();
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
