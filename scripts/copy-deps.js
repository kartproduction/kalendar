// Copies the PDF library into www/js so the app works fully offline.
const fs = require("fs");
fs.mkdirSync("www/js", { recursive: true });
fs.copyFileSync("node_modules/jspdf/dist/jspdf.umd.min.js", "www/js/jspdf.umd.min.js");
console.log("Copied jspdf.umd.min.js to www/js/");
