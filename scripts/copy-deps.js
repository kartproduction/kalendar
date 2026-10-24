const fs = require("fs");
const path = require("path");
const dir = "node_modules/jspdf/dist";
fs.mkdirSync("www/js", { recursive: true });
const files = fs.existsSync(dir) ? fs.readdirSync(dir) : [];
const pick =
  files.find((f) => f === "jspdf.umd.min.js") ||
  files.find((f) => /umd.*min\.js$/.test(f));
if (!pick) {
  console.error("jsPDF UMD build not found. dist contains:", files);
  process.exit(1);
}
fs.copyFileSync(path.join(dir, pick), "www/js/jspdf.umd.min.js");
console.log("Copied", pick, "to www/js/");
