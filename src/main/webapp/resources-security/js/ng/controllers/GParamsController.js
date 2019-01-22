angular.module('app').controller('GParamsController',
    ['$scope', '$http', 'GridManager', 'ModalManager', function ($scope, $http, GridManager, ModalManager) {

        angular.extend($scope, {
            listURL:'settings/all',
            saveURL:'settings/put',
            deleteURL:'settings/delete',
            init:{}
        });

        ModalManager.enableModals($scope);

        $scope.loadSettings = function () {
            $http.post($scope.listURL).success(function (response) {
                $scope.settingsList = response;
            })
        };

        $scope.loadSettings();

        $scope.showAddEdit = function (item) {
            $scope.init.action = item ? 'რედაქტირება' : 'დამატება';
            $scope.object = {};
            if (item) {
                $scope.object = angular.copy(item);
            }
            $('#showAddEdit').modal('show');
        };

        $scope.save = function () {
            var objectCopy = angular.copy($scope.object);
            $http.post($scope.saveURL, objectCopy).success(function (response) {
                if (!response.success) {
                    return;
                }
                $scope.showSuccessAlert("Success");
                $scope.loadSettings();
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
                $scope.loadSettings();
            });
        };

        $scope.createFabMenuItems = function () {
            setTimeout(function () {
                var add = $scope.showAddEdit;
                var scope = angular.element($(".fabmenu")).scope();
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
            }, 2000);
        };

        $scope.createFabMenuItems();
    }]);
