angular.module('app').controller('GMainVideoController',
    ['$scope', '$http', 'GridManager', 'ModalManager','FileUploader', function ($scope, $http, GridManager, ModalManager,FileUploader) {

        angular.extend($scope, {
            url:'main-video/first',
            saveURL:'main-video/put',
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
            }
        });

        ModalManager.enableModals($scope);

        $scope.loadMainVido = function(){
            $scope.object = {};
            $http.post($scope.url).success(function (data) {
                if(data.id != undefined){
                    $scope.object = data;
                }
            });
        };

        $scope.save = function () {
            var copyObject = angular.copy($scope.object);
            $http.post($scope.saveURL, copyObject).success(function (response) {
                if (!response.success) {
                    return;
                }
                $scope.showSuccessAlert("Success");
                $scope.loadMainVido();
            });
        };

        $scope.loadMainVido();


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

    }]);
