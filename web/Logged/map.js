function results(form) {
  build("p"+form.place.value, form.btype.value);
}

// form value manipulation
function changeSite(poly) {
  no=getNumber(poly);
  document.getElementById("place").value=no;
}

function build(site, building) {
  document.getElementById(site).src="img/" + building + ".png";
}

// get area number from area object
function getNumber(poly) {
  return poly.getAttribute("no");
}

function over(poly) {
  // change cursor to pointer
  document.body.style.cursor="url('first.cur'),pointer"
  no=getNumber(poly);
  img=document.getElementById("p"+no);
  src=img.getAttribute("src");
  if (src=="img/nop.png") {
    img.src="img/sha.png";
  }

}

function out(poly) {
  // change cursor back to automatic
  document.body.style.cursor="url('first.cur'),auto"
  no=getNumber(poly);
  img=document.getElementById("p"+no);
  src=img.getAttribute("src");
  if (src=="img/sha.png") {
    img.src="img/nop.png";
  }
}