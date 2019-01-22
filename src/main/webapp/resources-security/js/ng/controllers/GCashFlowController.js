/**
 * Created by Mikheil on 12/2/2017.
 */
angular.module('app').controller('GCashFlowController',
    ['$scope', '$http', 'GridManager', 'ModalManager', function ($scope, $http, GridManager, ModalManager) {

        angular.extend($scope, {
            cashFlowList: 'cash-flow/list',
            userList:'user/list?searchExpression=',
            init: {},
            filterObj:{}
        });

        ModalManager.enableModals($scope);
        $scope.filter = function () {
            var objCopy = angular.copy($scope.filterObj);
            $scope.url = $scope.cashFlowList + "?" + $.param(objCopy);
            GridManager.givePowerTo($scope);
            $scope.AmfTable.openPage(0);
        };

        $('#userSelect').autocomplete({
            minLength: 1,
            source: function(request, response) {
                $http.get($scope.userList+request.term).success(function(data){
                    var result = [];
                    angular.forEach(data.results, function(item) {
                        result.push({label: item.username+' '+item.userRoomCode, value:item.username+' '+item.userRoomCode, objId:item.id});
                    });
                    if(result.length == 0){
                        result.push({label: "მონაცემი არ მოიძებნა", value:"მონაცემი არ მოიძებნა", objId:null});
                    }
                    response(result);
                });
            },
            select: function (event, ui) {
                $scope.filterObj.userId = ui.item.objId;
            },
            open: function() {
                $("ul.ui-menu").width($(this).innerWidth());
            },
            appendTo: "#showAddEdit"
        });


    }]);