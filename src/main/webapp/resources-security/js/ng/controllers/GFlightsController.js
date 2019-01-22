/**
 * Created by Mikheil on 11/18/2017.
 */
angular.module('app').controller('GFlightsController',
    ['$scope', '$http', 'GridManager', 'ModalManager','FileUploader', function ($scope, $http, GridManager, ModalManager,FileUploader) {


        angular.extend($scope, {
            init:{},
            url:'flights/list',
            saveURL:'flights/put',
            deleteURL:'flights/delete',
            parcelListURL:'parcel/list/all',
            flightStateList:[
                {id:"PREPARING",name:"გამოსაგზავნად ემზადება"},
                {id:"SENT",name:"გამოგზავნილია"},
                {id:"DELIVERED",name:"ჩამოსულია"},
                {id:"DELAYED",name:"გადაიდო"}
            ]
        });

        GridManager.givePowerTo($scope);
        ModalManager.enableModals($scope);

        $scope.AmfTable.sortBy("recieveDate", true);
        $scope.AmfTable.openPage(0);


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

        $scope.loadParcelCoordinates = function () {
            $http.post($scope.parcelListURL).success(function (response) {
                  $scope.parcelList = response;
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

        $scope.upload = function (item) {
            $scope.uploaded = false;
            $("#importDialog").attr('flightId', item.id).modal('show')
        };

        $scope.uploadExcel = function () {

            var element = document.getElementById('import_file');

            var formData = new FormData();
            formData.append('excelfile', element.files[0]);
            var categoryId = $("#importDialog").attr('flightId');

            $http({
                url: 'goods/import?flightId=' + categoryId,
                method: "POST",
                data: formData,
                headers: {'Content-Type': undefined}
            }).success(function (response) {
                if (response.success) {
                    $scope.AmfTable.invalidateCache();
                    $scope.uploaded = true;
                    $scope.showSuccessAlert("ოპერაცია დასრულდა. გთხოვთ გადატვირთოთ გვერდი და შეამოწმეთ ჩანაწერები", true);
                } else {
                    $scope.showErrorModal("ოპერაცია ვერ შესრულდა. \n\r" + response.message)
                }
                $("#importDialog").modal('hide')
            });

        };


        $scope.createFabMenuItems();
        $scope.loadParcelCoordinates();

    }]);
