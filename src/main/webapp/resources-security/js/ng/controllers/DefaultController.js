

angular.module('app').controller('DefaultController',
    ['$scope', '$http', function ($scope, $http) {

        angular.extend($scope, {
            url:'stat/all',
            getGraph:"transactions/graphData",
            filter:{}
        });

        $scope.grapStyle = {
            colors: ['#a88add', '#0cc2aa', '#fcc100'],
            series: {shadowSize: 3},
            xaxis: {show: true, font: {color: '#ccc'}, position: 'bottom'},
            yaxis: {show: true, font: {color: '#ccc'}},
            grid: {hoverable: true, clickable: true, borderWidth: 0, color: 'rgba(120,120,120,0.5)'},
            tooltip: true,
            tooltipOpts: {
                content: '%x.0 ში ჩაირიცხა %y.4', defaultTheme: false, shifts: {x: 0, y: -40}
            }
        };

        $scope.graphData = [
            {
                data: [
                    [0,0],
                    [32,0]
                ],
                points: { show: true, radius: 3},
                splines: { show: true, tension: 0.45, lineWidth: 2, fill: 1 }
            }
        ];

        $scope.loadGraph = function () {
            var params =$.param($scope.filter);
            $http.get($scope.getGraph + ((params != undefined) ? "?"+params:"" )).success(function (response) {
                $scope.graphData[0].data = [];
                if(response.length > 0){
                    $scope.graphData[0].data.push([0,0]);
                    for(var i in response){
                        var array = [];
                        array.push(parseInt(Object.keys(response[i])[0]));
                        array.push(Object.values(response[i])[0]);
                        $scope.graphData[0].data.push(array);
                    }
                    $scope.graphData[0].data.push([32,0]);
                }
                setTimeout(function () {
                    $("#plotter").plot($scope.graphData, $scope.grapStyle);
                }, 1000);
            });
        };

        $scope.loadGraph();

        $http.get($scope.url).success(function (response) {
            $scope.stat = response;
        })


    }]);
