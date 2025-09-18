// ==================== Recipe Data ====================
const recipes = [
    // North Indian
    { name: "Paneer Butter Masala", category: "North Indian", isPopular: true, ingredients:["Paneer","Butter","Tomatoes","Cream","Spices"], instructions:"Cook paneer in tomato butter gravy and garnish with cream." },
    { name: "Dal Makhani", category: "North Indian", isPopular: true, ingredients:["Whole black lentils","Kidney beans","Butter","Cream","Spices"], instructions:"Slow cook dal and rajma with spices and finish with butter & cream." },
    { name: "Rajma Chawal", category: "North Indian", isPopular: true, ingredients:["Rajma","Rice","Onions","Tomatoes","Spices"], instructions:"Cook rajma curry and serve hot with rice." },
    { name: "Chole Bhature", category: "North Indian", isPopular: true, ingredients:["Chickpeas","Flour","Onion","Spices"], instructions:"Cook spicy chole and serve with fried bhature." },
    { name: "Butter Chicken", category: "North Indian", isPopular: true, ingredients:["Chicken","Butter","Tomatoes","Cream","Spices"], instructions:"Cook chicken in tomato butter gravy and finish with cream." },

    // South Indian
    { name: "Masala Dosa", category: "South Indian", isPopular: true, ingredients:["Dosa batter","Potato","Onion","Spices"], instructions:"Prepare dosa and fill with spiced potato masala." },
    { name: "Idli Sambar", category: "South Indian", isPopular: true, ingredients:["Rice","Urad dal","Tamarind","Vegetables","Spices"], instructions:"Steam idlis and serve with sambar." },
    { name: "Medu Vada", category: "South Indian", isPopular: false, ingredients:["Urad dal","Onion","Green chili","Spices"], instructions:"Grind dal, shape into vadas, and deep fry." },
    { name: "Pongal", category: "South Indian", isPopular: false, ingredients:["Rice","Moong dal","Pepper","Ghee","Cashews"], instructions:"Cook rice and dal with spices, garnish with ghee and cashews." },
    { name: "Chettinad Chicken Curry", category: "South Indian", isPopular: true, ingredients:["Chicken","Onions","Coconut","Chettinad spices"], instructions:"Cook chicken with roasted coconut masala and spices." },

    // Street Food
    { name: "Pani Puri", category: "Street Food", isPopular: true, ingredients:["Puris","Potato","Tamarind water","Spices"], instructions:"Fill puris with spicy potato mix and tangy water." },
    { name: "Pav Bhaji", category: "Street Food", isPopular: true, ingredients:["Mixed vegetables","Potato","Butter","Pav buns","Spices"], instructions:"Cook mashed veggies with butter masala and serve with pav." },
    { name: "Vada Pav", category: "Street Food", isPopular: true, ingredients:["Potato","Bread","Chili","Spices"], instructions:"Fry potato vada and serve inside pav with chutney." },
    { name: "Bhel Puri", category: "Street Food", isPopular: false, ingredients:["Puffed rice","Onion","Tomato","Chutneys","Sev"], instructions:"Mix puffed rice with chutneys and garnish with sev." },
    { name: "Chole Kulche", category: "Street Food", isPopular: true, ingredients:["Chickpeas","Kulcha bread","Onion","Spices"], instructions:"Serve spicy chole with soft kulchas." },

    // Desserts
    { name: "Gulab Jamun", category: "Desserts", isPopular: true, ingredients:["Khoya","Sugar","Cardamom","Oil"], instructions:"Fry khoya balls and soak in sugar syrup." },
    { name: "Rasgulla", category: "Desserts", isPopular: true, ingredients:["Chhena","Sugar","Water"], instructions:"Boil chhena balls in sugar syrup until spongy." },
    { name: "Kheer", category: "Desserts", isPopular: true, ingredients:["Rice","Milk","Sugar","Cardamom","Nuts"], instructions:"Cook rice in milk with sugar and garnish with nuts." },
    { name: "Jalebi", category: "Desserts", isPopular: false, ingredients:["Flour","Sugar","Saffron","Oil"], instructions:"Fry spiral batter and soak in sugar syrup." },
    { name: "Rasmalai", category: "Desserts", isPopular: true, ingredients:["Chhena","Milk","Sugar","Cardamom","Saffron"], instructions:"Soak soft chhena patties in sweetened saffron milk." }
];

// ==================== DOM Elements ====================
const recipesContainer = document.getElementById('recipes-container');
const searchBar = document.getElementById('search-bar');
const filterButtons = document.querySelectorAll('.filter-btn');
const detailsSection = document.getElementById('recipe-details');
const backBtn = document.getElementById('back-btn');

// ==================== Helpers ====================
const getCategoryStyle = (category) => {
    if (category === "Vegetarian") return "bg-green-500";
    if (category === "Non-Vegetarian") return "bg-red-500";
    if (category === "Indian") return "bg-orange-500";
    if (category === "South Indian") return "bg-yellow-600";
    if (category === "North Indian") return "bg-purple-600";
    if (category === "Street Food") return "bg-pink-500";
    if (category === "Desserts") return "bg-blue-500";
    return "bg-gray-500";
};

// ==================== Render Recipes ====================
const renderRecipes = (filtered = recipes) => {
    recipesContainer.innerHTML = '';
    filtered.forEach((recipe, index) => {
        const card = document.createElement('div');
        card.className = `${getCategoryStyle(recipe.category)} text-white rounded-lg shadow-md recipe-card flex flex-col text-left p-4`;
        card.innerHTML = `
            <h3 class="font-['Bangers'] text-xl mb-2">${recipe.name}</h3>
            <h4 class="font-semibold mb-1">Ingredients:</h4>
            <ul class="text-sm mb-2">${recipe.ingredients.map(i => `<li>${i}</li>`).join('')}</ul>
            <h4 class="font-semibold mb-1">Instructions:</h4>
            <p class="text-sm mb-4">${recipe.instructions}</p>
            <button class="view-btn bg-white text-gray-800 px-3 py-1 rounded-lg font-medium hover:bg-gray-200" data-id="${index}">View</button>
        `;
        recipesContainer.appendChild(card);
    });

    // Attach "View" button events
    document.querySelectorAll('.view-btn').forEach(btn => {
        btn.addEventListener('click', (e) => {
            const id = parseInt(e.target.dataset.id);
            const recipe = recipes[id];
            showDetails(recipe);
        });
    });
};

// ==================== Show Recipe Details ====================
const showDetails = (recipe) => {
    document.getElementById('main-content').classList.add('hidden');
    detailsSection.classList.remove('hidden');
    document.getElementById('details-title').textContent = recipe.name;
    document.getElementById('details-ingredients').innerHTML = recipe.ingredients.map(i => `<li>${i}</li>`).join('');
    document.getElementById('details-instructions').textContent = recipe.instructions;
};

// Back Button
backBtn.addEventListener('click', () => {
    detailsSection.classList.add('hidden');
    document.getElementById('main-content').classList.remove('hidden');
});

// ==================== Filter & Search ====================
const filterAndSearch = () => {
    const searchTerm = searchBar.value.toLowerCase();
    const activeCategory = document.querySelector('.filter-btn[data-active="true"]').dataset.category;

    let filtered = recipes;
    if (activeCategory === 'Popular') filtered = filtered.filter(r => r.isPopular);
    else if (activeCategory !== 'All') filtered = filtered.filter(r => r.category === activeCategory);

    filtered = filtered.filter(r => r.name.toLowerCase().includes(searchTerm));
    renderRecipes(filtered);
};

// ==================== Events ====================
window.addEventListener('load', () => {
    setTimeout(() => {
        document.getElementById('splash-screen').classList.add('hidden');
        renderRecipes();
    }, 2500);
});

searchBar.addEventListener('input', filterAndSearch);

filterButtons.forEach(button => button.addEventListener('click', (e) => {
    filterButtons.forEach(btn => btn.dataset.active = "false");
    e.target.dataset.active = "true";
    filterAndSearch();
}));