//const for picture
const defaultButton = document.querySelector(".default")
const defaultPhoto = document.querySelector("#img");
const defaultPhotoSrc = defaultPhoto.src;
const uploadButton = document.querySelector(".upload-picture");
const cancelBtn = document.querySelector(".cancel");
const img = document.querySelector("#img");

//const for tab
var tabButtons = document.querySelectorAll(".tab-container .button-container button");
var tabPanels = document.querySelectorAll(".tab-panel");


function defaultBtnActive(){
    defaultButton.click();
}

defaultButton.addEventListener("change", function(){
    const file = this.files[0];
    if(file){
        const reader = new FileReader();
        reader.onload = function(){
            const result = reader.result;
            img.src = result;
            img.classList.add("active");
        }
        cancelBtn.addEventListener("click", function(){
            img.src = defaultPhotoSrc;
            img.classList.remove("active");
        });
        reader.readAsDataURL(file);
    }
    if(this.value){
        let valueStore = this.value.match(regExp);
        fileName.textContent = valueStore;
    }
});

function showPanel(panelIndex, colorCode) {
     
    tabButtons.forEach(function(node) {
       node.style.color="";
    });
    
    tabButtons[panelIndex].style.color= colorCode;

    tabPanels.forEach(function(node){
       node.style.display="none";
    });
    tabPanels[panelIndex].style.display="block";
}

showPanel(0,'#FCDFA6');


//change password
const cancelPasswordButton = document.querySelector(".cancel-change");
const editPasswordButton = document.querySelector(".edit");
const changePasswordPanel = document.querySelector(".change-password");
const message = document.querySelector(".message");

editPasswordButton.addEventListener("click", function(){
    changePasswordPanel.classList.add("active");
    editPasswordButton.classList.add("remove");
    message.classList.add("remove");
});

cancelPasswordButton.addEventListener("click", function(){
    changePasswordPanel.classList.remove("active");
    editPasswordButton.classList.remove("remove");
    message.classList.remove("remove");
    location.reload(true);
});

//hide and show  password

const oldpass = document.querySelector("#oldpass");
const newpass = document.querySelector("#newpass");
const retypepass = document.querySelector("#retypepass");

function togglePassword(field){
    if (field.type === "password") {
        field.type = "text";
      } else {
        field.type = "password";
      }
}

//adding a payment option

const addPayment = document.querySelector(".add-payment");
const cancelAdd = document.querySelector(".cancel-payment-add");
const addPanelPayment = document.querySelector(".add-payment-option");
const header = document.querySelector(".header");
const main = document.querySelector(".payment-details");
const left = document.querySelector(".left");
const body = document.querySelector(".body");

addPayment.addEventListener("click", function () {
    addPanelPayment.classList.add("active");
    main.style.filter = "blur(20px)";
    left.style.filter = "blur(20px)";
    header.style.filter = "blur(20px)";
    body.style.overflow = "hidden";
});

cancelAdd.addEventListener("click", function () {
    addPanelPayment.classList.remove("active");
    main.style.filter = "blur(0px)";
    left.style.filter = "blur(0px)";
    header.style.filter = "blur(0px)";
    body.style.overflow = "auto";
    location.reload();
});

