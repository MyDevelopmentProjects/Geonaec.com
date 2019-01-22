angular.module('app').controller('EventsController',
    ['$scope', '$http', 'GridManager', 'ModalManager','FileUploader', function ($scope, $http, GridManager, ModalManager, FileUploader) {

        angular.extend($scope, {
            listURL:'events/all',
            saveURL:'events/put',
            init:{}
        });

        ModalManager.enableModals($scope);

        $scope.loadAllSliders = function () {
            $http.get($scope.listURL).success(function (response) {
                $scope.sliderList = response;
            })
        };

        $scope.loadAllSliders();

        $scope.showAddEdit = function (item) {
            $scope.init.action = item ? 'რედაქტირება' : 'დამატება';
            $scope.object = {};
            if (item) {
                $scope.object = angular.copy(item);
            }
            $('#showAddEdit').modal('show');
        };

        $scope.save = function () {
            if($scope.uploader.queue.length > 0){
                uploader.uploadAll();
            } else {
                if($scope.object.image === undefined || $scope.object.image == null){
                    $scope.showErrorModal("Please upload image.");
                    return
                }
                $scope.saveLazy();
            }
        };

        $scope.saveLazy = function () {
            $http.post($scope.saveURL, $scope.object).success(function (response) {
                if (!response.success) {
                    $scope.showErrorModal("მოცემული ჩანაწერის დამატება შეუძლებელია");
                    return;
                }
                $scope.loadAllSliders();
                $('#showAddEdit').modal('hide');
            });
        };

        var uploader = $scope.uploader = new FileUploader({
            url: 'upload/file'
        });

        // FILTERS
        uploader.filters.push({
            name: 'imageFilter',
            fn: function(item /*{File|FileLikeObject}*/, options) {
                var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
                return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
            }
        });

        // CALLBACKS
        uploader.onBeforeUploadItem = function(item) {
            item.formData.push({type: 'SLIDER_IMG'});
        };

        uploader.onCompleteItem = function(fileItem, response, status, headers) {
            if(response.success){
                if(response.source.length > 0){
                    $scope.object.image = response.source[0];
                }
            }
        };

        uploader.onCompleteAll = function() {
            $("input[type='file']").val('').clone(true);
            $scope.saveLazy();
        };

        $('#showAddEdit').on("hidden.bs.modal", function(){
            uploader.queue = [];
            // Clear File Input
            $("input[type='file']").val('').clone(true);
        });


        $scope.createFabMenuItems = function () {
            setTimeout(function () {
                var scope = angular.element($(".fabmenu")).scope();
                if(scope !== undefined){
                    scope.$apply(function () {
                        scope.app.fabScope = $scope;
                        $scope.fabMenuItems = scope.app.fabMenuItems = [

                        ];
                    });
                }
            }, 1000);
        };
        $scope.createFabMenuItems();

    }]);