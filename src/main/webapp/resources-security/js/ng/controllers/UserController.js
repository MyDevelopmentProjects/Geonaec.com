angular.module('app').controller('UserController',
    ['$scope', '$http', 'GridManager', 'ModalManager','FileUploader', function ($scope, $http, GridManager, ModalManager, FileUploader) {

        angular.extend($scope, {
            url:'user/list',
            saveURL:'user/put',
            deleteURL:'user/delete',
            roleURL: 'role/list',
            imageUploaded: false,
            init:{}
        });

        GridManager.givePowerTo($scope);
        ModalManager.enableModals($scope);
        $scope.AmfTable.openPage(0);

        $scope.showAddEdit = function (item) {
            $scope.init.action = item ? 'რედაქტირება' : 'დამატება';
            $scope.object = {};
            $scope.imageUploaded = false;
            if (item) {
                $scope.object = angular.copy(item);
            }
            $scope.loadRoles();
            $('#showAddEdit').modal('show');
        };

        $scope.save = function () {
            var file = $("input[type='file']");
            if(!$scope.imageUploaded && file && file[0].files.length > 0){
                uploader.uploadAll();
            } else {
                if($scope.object.imgURL == undefined || $scope.object.imgURL == null) {
                    $scope.showErrorModal("Please select image.");
                    return
                }
                $scope.sendSave();
            }
        };

        $scope.sendSave = function () {
            var userCopy = angular.copy($scope.object);

            if(userCopy.newpassword && userCopy.newpassword.length >= 8){
                userCopy.password = userCopy.newpassword;
                delete userCopy.newpassword;
                delete userCopy.verifynewpassword;
            }
            delete userCopy.verify_password;

            $http.post($scope.saveURL, userCopy).success(function (response) {
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

        $scope.loadRoles = function(){
            $http.get($scope.roleURL).success(function(response){
                if (!response.success) {
                    return;
                }
                $scope.role_list = response.results;
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
            item.formData.push({type: 'USER_IMG'});
        };
        uploader.onCompleteItem = function(fileItem, response, status, headers) {
            if(response.success){
                if(response.source.length > 0){
                    $scope.object.imgURL = response.source[0];
                }
            }
        };
        uploader.onCompleteAll = function() {
            // Clear File Input
            $("input[type='file']").val('').clone(true);
            $scope.imageUploaded = true;
            $scope.sendSave();
        };
        $('#showAddEdit').on("hidden.bs.modal", function(){
            uploader.queue = [];
            // Clear File Input
            $("input[type='file']").val('').clone(true);
        });

    }]);
