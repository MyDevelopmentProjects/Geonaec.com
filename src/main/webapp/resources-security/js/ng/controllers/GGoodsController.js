/**
 * Created by Mikheil on 11/18/2017.
 */
angular.module('app').controller('GGoodsController',
    ['$scope', '$http', 'GridManager', 'ModalManager','FileUploader', function ($scope, $http, GridManager, ModalManager,FileUploader) {

        angular.extend($scope, {
            init:{},
            url:'goods/list',
            saveURL:'goods/put',
            deleteURL:'goods/delete',
            flightsList:'flights/list?searchExpression=',
            currencyList:'currency/list?searchExpression=',
            countryList:'country/list?searchExpression=',
            userList:'user/list?searchExpression=',
            goodsStateList:[
                {id:"WAITING",name:"გამოსაგზავნად ემზადება"},
                {id:"WAREHOUSE",name:"საწყობშია"},
                {id:"SENT",name:"გამოგზავნილია"},
                {id:"RECIEVED",name:"ჩამოსულია"},
                {id:"TOOK_OUT",name:"გატანილია"}
            ],
            selectFlightId: null,
            selectCountryId: null,
            selectCurrencyId:null,
            selectUserId:null
        });

        $('#flightSelect').autocomplete({
            minLength: 1,
            source: function(request, response) {
                $http.get($scope.flightsList+request.term).success(function(data){
                    var result = [];
                    angular.forEach(data.results, function(item) {
                        result.push({label: item.flightNumber, value:item.flightNumber, objId:item.id});
                    });
                    if(result.length == 0){
                        result.push({label: "მონაცემი არ მოიძებნა", value:"მონაცემი არ მოიძებნა", objId:null});
                    }
                    response(result);
                });
            },
            select: function (event, ui) {
                $scope.selectFlightId = ui.item.objId;
            },
            open: function() {
                $("ul.ui-menu").width($(this).innerWidth());
            },
            appendTo: "#showAddEdit"
        });

        $('#countrySelect').autocomplete({
            minLength: 1,
            source: function(request, response) {
                $http.get($scope.countryList+request.term).success(function(data){
                    var result = [];
                    angular.forEach(data.results, function(item) {
                        result.push({label: item.translation.titleGE, value:item.translation.titleGE, objId:item.id});
                    });
                    if(result.length == 0){
                        result.push({label: "მონაცემი არ მოიძებნა", value:"მონაცემი არ მოიძებნა", objId:null});
                    }
                    response(result);
                });
            },
            select: function (event, ui) {
                $scope.selectCountryId = ui.item.objId;
            },
            open: function() {
                $("ul.ui-menu").width($(this).innerWidth());
            },
            appendTo: "#showAddEdit"
        });

        $('#currencySelect').autocomplete({
            minLength: 1,
            source: function(request, response) {
                $http.get($scope.currencyList+request.term).success(function(data){
                    var result = [];
                    angular.forEach(data.results, function(item) {
                        result.push({label: item.shortCode, value:item.shortCode, objId:item.id});
                    });
                    if(result.length == 0){
                        result.push({label: "მონაცემი არ მოიძებნა", value:"მონაცემი არ მოიძებნა", objId:null});
                    }
                    response(result);
                });
            },
            select: function (event, ui) {
                $scope.selectCurrencyId = ui.item.objId;
            },
            open: function() {
                $("ul.ui-menu").width($(this).innerWidth());
            },
            appendTo: "#showAddEdit"
        });

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
                $scope.selectUserId = ui.item.objId;
            },
            open: function() {
                $("ul.ui-menu").width($(this).innerWidth());
            },
            appendTo: "#showAddEdit"
        });

        GridManager.givePowerTo($scope);
        ModalManager.enableModals($scope);
        $scope.AmfTable.openPage(0);

        $scope.showAddEdit = function (item) {
            $scope.init.action = item ? 'რედაქტირება' : 'დამატება';
            $scope.object = {};
            if (item) {
                $scope.object = angular.copy(item);

                if($scope.object.flights != null){
                    $("#flightSelect").val($scope.object.flights.flightNumber);
                }
                if($scope.object.country != null) {
                    $("#countrySelect").val($scope.object.country.translation.titleGE);
                }
                if($scope.object.currency != null) {
                    $("#currencySelect").val($scope.object.currency.shortCode);
                }
                if($scope.object.user != null) {
                    $("#userSelect").val($scope.object.user.username + ' ' + $scope.object.user.userRoomCode);
                }
            }
            $('#showAddEdit').modal('show');
        };


        $scope.save = function () {
            var copyObj = angular.copy($scope.object);
            if($scope.selectFlightId != null){
                copyObj.flights = {};
                copyObj.flights.id = $scope.selectFlightId;
            }

            if($scope.selectCountryId != null){
                copyObj.country = {};
                copyObj.country.id = $scope.selectCountryId;
            }
            if($scope.selectCurrencyId != null){
                copyObj.currency = {};
                copyObj.currency.id = $scope.selectCurrencyId;
            }
            if($scope.selectUserId != null){
                copyObj.user = {};
                copyObj.user.id = $scope.selectUserId;
            }

            $http.post($scope.saveURL, copyObj).success(function (response) {
                if (!response.success) {
                    return;
                }
                $scope.showSuccessAlert("Success");
                $scope.AmfTable.reloadData(true);
                $('#showAddEdit').modal('hide');
            });
        };

        $scope.delete = function (itemId) {
            $http.post($scope.deleteURL, itemId).
            success(function (data) {
                if (!data.success) {
                    if(response.errorMessage == "RECORD_IS_USED_IN_OTHER_TABLES"){
                        $scope.showErrorModal("მოცემული ჩანაწერის წაშლა შეუძლებელია რადგან ის ფიქსირდება სხვა ცხრილშიც.")
                    }
                    return;
                }
                $scope.AmfTable.reloadData(true);
            });
        };


        $scope.createFabMenuItems = function () {
            setTimeout(function () {
                var add = $scope.showAddEdit;
                var scope = angular.element($(".fabmenu")).scope();
                if(scope !== undefined){
                    scope.$apply(function () {
                        scope.app.fabScope = $scope;
                        $scope.fabMenuItems = scope.app.fabMenuItems = [
                            {
                                callback: add,
                                "fa": "plus",
                                "title": "დამატება"
                            }
                        ];
                    });
                }
            }, 1000);
        };

        $scope.createFabMenuItems();
    }]);
