document.addEventListener('DOMContentLoaded', function() {

    document.getElementById("lang_en").addEventListener("click", changeLangEn);
    document.getElementById("lang_ua").addEventListener("click", changeLangUa);

});

function changeLangEn() {
    changeLang("en");
}

function changeLangUa() {
    changeLang("ua");
}

String.prototype.replaceAt=function(index, replacement) {
    return this.substr(0, index) + replacement+ this.substr(index + replacement.length);
};

function changeLang(lang) {
    if(window.location.href.indexOf("?") !== -1){
        let index = window.location.href.indexOf("lang");
        if(index !== -1)
            window.location.replace(window.location.href.replaceAt(index,"lang="+lang));
        else
            window.location.replace(window.location.href + "&lang=" + lang);
    } else {
        window.location.replace(window.location.href + "?lang=" + lang);
    }
}