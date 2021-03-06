var chartDataStr =  decodeHtml(chartData);
var chartJsonArray = JSON.parse(chartDataStr);

var arrayLength = chartJsonArray.length;
var numericData = [];
var labelData = [];

for(i=0; i < arrayLength; i++){
	numericData[i] = chartJsonArray[i].value;
	labelData[i] = chartJsonArray[i].label;
}

//For a pie chart
new Chart(document.getElementById("myChart"),{
  type: "pie",
  data: {
    labels: labelData,
    datasets:  [{
      label: 'My First dataset',
      backgroundColor: ["red", "green","blue"],
      data: numericData
    }]
  },
  options: {
    title: {
      display: true,
      text: "Project Statuses"
    }
  }
});

function decodeHtml(html){
	var txt = document.createElement("textarea");
	txt.innerHTML = html;
	return txt.value;
}