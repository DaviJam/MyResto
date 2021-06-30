let filters = document.querySelectorAll(".filter");
console.log(filters);
filters.forEach(filter => filter.addEventListener('click', event => {
  filterProduct(event.target);
}));

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