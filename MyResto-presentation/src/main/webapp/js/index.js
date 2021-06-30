var DEBUG_MODE = true;

//Filtre page accueil
let filters = document.querySelectorAll(".filter");
if (DEBUG_MODE) {console.log(filters);}
filters.forEach(filter => filter.addEventListener('click', event => {
  filterProduct(event.target);
}));

//Bouton quantité page gestion stock
let qqt = document.querySelectorAll(".qtt_stock");
if (DEBUG_MODE) {console.log(qqt);}
qqt.forEach(q => q.addEventListener('click', event => {
    changeQqt(event.target);
}));

function changeQqt(qtt) {
    if (qtt.getAttribute("direction") == "bottom") {
        qtt.parentElement.querySelector("input").value = parseInt(qtt.parentElement.querySelector("input").value) - 1;
    } else {
        qtt.parentElement.querySelector("input").value = parseInt(qtt.parentElement.querySelector("input").value) + 1;
    }
}

function filterProduct(filter) {
    let cnt = 0;
    let allCard = document.querySelectorAll(".card");
    let categories = document.querySelectorAll(".filter");
    categories.forEach(category => category.classList.remove("active"));
    filter.classList.add("active");
    filter = filter.getAttribute("data-category");
    allCard.forEach(function(card){
      if (card.getAttribute("data-category") != filter && filter != "*") {card.style.display = "none";} else {card.style.display = "initial"; cnt += 1;}
    });

    document.querySelector(".defaut-title").innerText = cnt + " produits trouvés";
}