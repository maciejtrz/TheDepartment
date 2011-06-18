function build(site, building) {
  document.getElementById(site).src="img/" + building + ".png";
}

// get area number from area object
function getNumber(poly) {
  return poly.getAttribute("no");
}

// AJAX function
function queryServer(position,fn)
{
  $.post("/TheDepartment/Logged/BuildingQuery", {"position": position}, function(data) {
      fn(position, data);
  });
}

function refreshAll() {
  for (i=1;i<=16;i++) {
  queryServer(i, refresh);
  }
}

function refresh(position, building) {
  build ("p"+position, building);
}

function clickSite(poly) {
  no=getNumber(poly);
  queryServer(no,dialog);
}

function dialog(position, building) {
  eval(building + ".show()");
}

function over(poly) {
  // change cursor to pointer
  document.body.style.cursor="pointer"
  no=getNumber(poly);
  img=document.getElementById("p"+no);
  src=img.getAttribute("src");
  if (src=="img/nop.png") {
    img.src="img/sha.png";
  }z

}

function out(poly) {
  // change cursor back to automatic
  document.body.style.cursor="auto"
  no=getNumber(poly);
  img=document.getElementById("p"+no);
  src=img.getAttribute("src");
  if (src=="img/sha.png") {
    img.src="img/nop.png";
  }
}