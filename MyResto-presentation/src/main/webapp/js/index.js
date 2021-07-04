var DEBUG_MODE = true;

//Bulle nombre de produit dans le panier
document.querySelector(".cardCount").innerText = document.querySelectorAll("div.product.no-float").length;

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

//Status d'une commande
let status = document.querySelectorAll(".product-status");
if (DEBUG_MODE) {console.log(status);}
status.forEach(function(s){
    switch (s.innerText) {
      case 'En attente':
        s.style.borderColor = "#818181";
        s.style.color = "#818181";
        break;
      case 'En cours':
        s.style.borderColor = "#ffc529";
        s.style.color = "#ffc529";
        break;
      case 'Terminé':
        s.style.borderColor = "#5dc157";
        s.style.color = "#5dc157";
        break;
      case 'Annulé':
        s.style.borderColor = "#fd734e";
        s.style.color = "#fd734e";
        break;
      default:
        console.log(`Le status de la commande ne correspond pas`);
    }
});

//Ajout d'un produit au panier
let formAddToCard = document.querySelectorAll(".addToCard");
if (DEBUG_MODE) {console.log(formAddToCard);}
formAddToCard.forEach(f => f.addEventListener('submit', event => {
  event.preventDefault();
  var request = new XMLHttpRequest();
  var url = "card_add";
  request.open("POST", url, true);
  request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
  request.onreadystatechange = function () {
      if (request.readyState === 4 && request.status === 200) {
        document.getElementById("modal_card").innerHTML = request.responseText;
        let bubble = document.querySelector(".cardCount");
        bubble.innerText = document.querySelectorAll("div.product.no-float").length;
      }
  };
  let form = event.target;
  let data = new FormData(form);
  let formObj = serialize(data);

  request.send("product=" + formObj.product);
}));

//Suppression d'un produit au panier
function card_delete(elem) {
  var request = new XMLHttpRequest();
  var url = "card_delete";
  request.open("POST", url, true);
  request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
  request.onreadystatechange = function () {
      if (request.readyState === 4 && request.status === 200) {
        document.getElementById("modal_card").innerHTML = request.responseText;
        let bubble = document.querySelector(".cardCount");
        bubble.innerText = document.querySelectorAll("div.product.no-float").length;
      }
  };

  request.send("id=" + elem.getAttribute("data"));
}

function changeQqt(qtt) {
    if (qtt.getAttribute("direction") == "bottom") {
        qtt.parentElement.querySelector("input").value = parseInt(qtt.parentElement.querySelector("input").value) - 1;
    } else {
        qtt.parentElement.querySelector("input").value = parseInt(qtt.parentElement.querySelector("input").value) + 1;
    }
}

function filterProduct(filter) {
debugger;
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

function serialize (data) {
	let obj = {};
	for (let [key, value] of data) {
		if (obj[key] !== undefined) {
			if (!Array.isArray(obj[key])) {
				obj[key] = [obj[key]];
			}
			obj[key].push(value);
		} else {
			obj[key] = value;
		}
	}
	return obj;
}
