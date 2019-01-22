angular.module('app').controller('GalleryController',
    ['$scope', '$http', 'GridManager', 'FileUploader', 'ModalManager',
    function ($scope, $http, GridManager, FileUploader, ModalManager) {

        angular.extend($scope, {
            saveURL: 'put/gallery',
            fileArr: []
        });

        ModalManager.enableModals($scope);
        GridManager.givePowerTo($scope);

        $scope.removeFile = function(at) {
            $scope.fileArr.splice(at, 1);
        };

        $scope.save = function () {
            var copyObject = angular.copy($scope.object);
            $scope.arr = angular.copy($scope.fileArr);
            copyObject.image1 = $scope.arr[0];
            copyObject.image2 = $scope.arr[1];
            copyObject.image3 = $scope.arr[2];
            copyObject.image4 = $scope.arr[3];
            copyObject.image5 = $scope.arr[4];
            $http.post($scope.saveURL, copyObject).success(function (response) {
                if (!response.success) {
                    return;
                }
                $scope.showSuccessAlert("Success");
                $scope.loadAll();
            });
        };

        $scope.loadAll = function () {
            $scope.data = {};
            $scope.object = {};
            $scope.fileArr = [];
            $http.get('get/gallery').success(function (data) {
                if (data.id !== undefined) {
                    $scope.object = data;
                    if (data.image1) $scope.fileArr.push(data.image1);
                    if (data.image2) $scope.fileArr.push(data.image2);
                    if (data.image3) $scope.fileArr.push(data.image3);
                    if (data.image4) $scope.fileArr.push(data.image4);
                    if (data.image5) $scope.fileArr.push(data.image5);
                }
            })
        };

        var imageUploaderMultiple = $scope.uploaderMultiple = new FileUploader({
            url: 'upload/file'
        });

        imageUploaderMultiple.onBeforeUploadItem = function(item) {
            item.formData.push({type: 'GENERAL'});
        };

        imageUploaderMultiple.onCompleteItem = function(fileItem, response, status, headers) {
            if(response.success){
                if(response.source.length > 0){
                    $scope.fileArr.push(response.source[0]);
                }
            }
        };

        imageUploaderMultiple.onCompleteAll = function() {
            $(".uplMult").val('').clone(true);
            $scope.save();
        };

        $scope.saveLazyMult = function () {
            if($scope.uploaderMultiple.queue.length > 0){
                imageUploaderMultiple.uploadAll();
            } else {
                $scope.save();
            }
        };

        $scope.loadAll();

    }]);
