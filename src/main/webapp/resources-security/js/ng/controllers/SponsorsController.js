angular.module('app').controller('SponsorsController',
    ['$scope', '$http', 'GridManager', 'FileUploader', 'ModalManager',
        function ($scope, $http, GridManager, FileUploader, ModalManager) {
            GridManager.givePowerTo($scope);

            angular.extend($scope, {
                saveURL: 'sponsors/put',
                fileArr: [],
                hrefArr: [],
                hrefIndex: -1
            });

            ModalManager.enableModals($scope);
            GridManager.givePowerTo($scope);

            $scope.removeFile = function (at) {
                $scope.fileArr.splice(at, 1);
            };

            $scope.showAddEdit = function (item) {
                $scope.href = null;
                $scope.hrefIndex = -1;
                if (item !== null) {
                    $scope.hrefIndex = item;
                    $scope.href = $scope.object['href'+(item+1)]
                }
                $('#showAddEdit').modal('show');
            };

            $scope.save = function () {
                var copyObject = angular.copy($scope.object);
                $scope.arr = angular.copy($scope.fileArr);
                $scope.hrefs = angular.copy($scope.hrefArr);

                if ($scope.hrefIndex > -1) {
                    $scope.hrefs[$scope.hrefIndex] = $scope.href;
                }

                copyObject.image1 = $scope.arr[0];
                copyObject.image2 = $scope.arr[1];
                copyObject.image3 = $scope.arr[2];
                copyObject.image4 = $scope.arr[3];
                copyObject.image5 = $scope.arr[4];
                copyObject.image6 = $scope.arr[5];

                copyObject.href1 = $scope.hrefs[0];
                copyObject.href2 = $scope.hrefs[1];
                copyObject.href3 = $scope.hrefs[2];
                copyObject.href4 = $scope.hrefs[3];
                copyObject.href5 = $scope.hrefs[4];
                copyObject.href6 = $scope.hrefs[5];
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
                $scope.hrefArr = [];
                $http.get('sponsors/first').success(function (data) {
                    if (data.id !== undefined) {
                        $scope.object = data;

                        if (data.image1) $scope.fileArr.push(data.image1);
                        if (data.image2) $scope.fileArr.push(data.image2);
                        if (data.image3) $scope.fileArr.push(data.image3);
                        if (data.image4) $scope.fileArr.push(data.image4);
                        if (data.image5) $scope.fileArr.push(data.image5);
                        if (data.image6) $scope.fileArr.push(data.image6);

                        if (data.href1) $scope.hrefArr.push(data.href1);
                        if (data.href2) $scope.hrefArr.push(data.href2);
                        if (data.href3) $scope.hrefArr.push(data.href3);
                        if (data.href4) $scope.hrefArr.push(data.href4);
                        if (data.href5) $scope.hrefArr.push(data.href5);
                        if (data.href6) $scope.hrefArr.push(data.href6);
                    }
                })
            };

            var imageUploaderMultiple = $scope.uploaderMultiple = new FileUploader({
                url: 'upload/file'
            });

            imageUploaderMultiple.onBeforeUploadItem = function (item) {
                item.formData.push({type: 'GENERAL'});
            };

            imageUploaderMultiple.onCompleteItem = function (fileItem, response, status, headers) {
                if (response.success) {
                    if (response.source.length > 0) {
                        $scope.fileArr.push(response.source[0]);
                    }
                }
            };

            imageUploaderMultiple.onCompleteAll = function () {
                $(".uplMult").val('').clone(true);
                $scope.save();
            };

            $scope.saveLazyMult = function () {
                if ($scope.uploaderMultiple.queue.length > 0) {
                    imageUploaderMultiple.uploadAll();
                } else {
                    $scope.save();
                }
            };

            $scope.loadAll();


        }]);
