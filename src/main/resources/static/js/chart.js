//
//var chartDataStr = decodeHTML(chartData);
//var chartJsonArray = JSON.parse(chartDataStr);
//
//var arrayLength = chartJsonArray.length;
//
//var numericData = [];
//var labelData = [];
//
//for(var i = 0; i < arrayLength; i++) {
//    numericData[i] = chartJsonArray[i].value;
//    labelData[i] = chartJsonArray[i].label;
//}
//
//new Chart(document.getElementById("myChart"), {
//    type: 'pie',
//     data: {
//            labels: ['Read', 'Currently reading', 'Want to read'],
//            datasets: [{
//                label: 'My First dataset',
//                 backgroundColor: ['#FF1493', 'yellow', 'rgb(218, 112, 214)'],
//                data: numericData
//            }]
//        },
//
//        options: {
//           title: {
//              display: true,
//              text: "Book Overview"
//              }
//        }
//});
//
//function decodeHTML(html){
//   var txt =  document.createElement("textarea")
//   txt.innerHTML = html;
//
//   return txt.value;
//}