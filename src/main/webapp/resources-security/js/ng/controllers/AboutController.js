angular.module('app').controller('AboutController',
    ['$scope', '$http', 'GridManager', 'ModalManager','FileUploader',
        function ($scope, $http, GridManager, ModalManager,FileUploader) {

        angular.extend($scope, {
            url:'about-us/first',
            saveURL:'about-us/put',
            uploadPath:"/upload/files/",
            init:{},
            options:{
                height: 300,
                toolbar: [
                    ['edit',['undo','redo']],
                    ['headline', ['style']],
                    ['style', ['bold', 'italic', 'underline', 'superscript', 'subscript', 'strikethrough', 'clear']],
                    ['fontface', ['fontname']],
                    ['textsize', ['fontsize']],
                    ['fontclr', ['color']],
                    ['alignment', ['ul', 'ol', 'paragraph', 'lineheight']],
                    ['height', ['height']],
                    ['table', ['table']],
                    ['insert', ['link','picture','video','hr']],
                    ['view', ['fullscreen', 'codeview']],
                    ['help', ['help']]
                ]
            },
            fileArr: []
        });

        ModalManager.enableModals($scope);

        $scope.loadAbout = function(){
            $scope.object = {};
            $scope.fileArr = [];
            $http.post($scope.url).success(function (data) {
                if(data.id !== undefined){
                    $scope.object = data;
                    if(data.image1) $scope.fileArr.push(data.image1)
                    if(data.image2) $scope.fileArr.push(data.image2)
                    if(data.image3) $scope.fileArr.push(data.image3)
                    if(data.image4) $scope.fileArr.push(data.image4)
                }
            });
        };

        $scope.removeFile = function(at) {
            $scope.fileArr.splice(at, 1);
        };

        $scope.save = function () {
            var copyObject = angular.copy($scope.object);
            $scope.arr = angular.copy($scope.fileArr);
            copyObject.image1 = $scope.arr[0]
            copyObject.image2 = $scope.arr[1]
            copyObject.image3 = $scope.arr[2]
            copyObject.image4 = $scope.arr[3]
            $http.post($scope.saveURL, copyObject).success(function (response) {
                if (!response.success) {
                    return;
                }
                $scope.showSuccessAlert("Success");
                $scope.loadAbout();
            });
        };

        $scope.loadAbout();


        var AboutUploader = $scope.uploader = new FileUploader({
            url: 'upload/file'
        });

        // CALLBACKS
        AboutUploader.onBeforeUploadItem = function(item) {
            item.formData.push({type: 'GENERAL'});
        };

        $scope.imageUploadEN = function(files, editor) {
            for(var i in files){
                AboutUploader.addToQueue(files[i]);
            }
            AboutUploader.uploadAll();
            AboutUploader.onCompleteItem = function(fileItem, response, status, headers) {
                if(response.success){
                    if(response.source.length > 0){
                        for(var file in response.source){
                            var fileName = response.source[file];
                            editor.insertImage($scope.editableEN, $scope.uploadPath+fileName, fileName);
                        }
                    }
                }
            };
        };

        $scope.imageUploadRU = function(files, editor) {
            for(var i in files){
                AboutUploader.addToQueue(files[i]);
            }
            AboutUploader.uploadAll();
            AboutUploader.onCompleteItem = function(fileItem, response, status, headers) {
                if(response.success){
                    if(response.source.length > 0){
                        for(var file in response.source){
                            var fileName = response.source[file];
                            editor.insertImage($scope.editableRU, $scope.uploadPath+fileName, fileName);
                        }
                    }
                }
            };
        };

        $scope.imageUploadGE = function(files, editor) {
            for(var i in files){
                AboutUploader.addToQueue(files[i]);
            }
            AboutUploader.uploadAll();
            AboutUploader.onCompleteItem = function(fileItem, response, status, headers) {
                if(response.success){
                    if(response.source.length > 0){
                        for(var file in response.source){
                            var fileName = response.source[file];
                            editor.insertImage($scope.editableGE, $scope.uploadPath+fileName, fileName);
                        }
                    }
                }
            };
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

    }]);
