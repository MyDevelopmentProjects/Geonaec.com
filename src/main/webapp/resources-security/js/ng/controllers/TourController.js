angular.module('app').controller('TourController',
    ['$scope', '$http', 'GridManager', 'ModalManager','FileUploader', function ($scope, $http, GridManager, ModalManager, FileUploader) {

        angular.extend($scope, {
            url:'news/list',
            saveURL:'news/put',
            deleteURL:'news/delete',
            options:{
                height: 150,
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
            adultList:[
                {id:1,name:'1'},
                {id:2,name:'2'},
                {id:3,name:'3'},
                {id:4,name:'4'},
                {id:5,name:'5'},
                {id:5,name:'6'},
                {id:5,name:'7'},
                {id:5,name:'8'}
            ],
            childList:[
                {id:1,name:'1'},
                {id:2,name:'2'},
                {id:3,name:'3'},
                {id:4,name:'4'},
                {id:5,name:'5'},
                {id:5,name:'6'},
                {id:5,name:'7'},
                {id:5,name:'8'}
            ],
            init:{}
        });

        GridManager.givePowerTo($scope);
        ModalManager.enableModals($scope);
        $scope.AmfTable.openPage(0);

        $scope.showAddEdit = function (item) {
            $scope.init.action = item ? 'რედაქტირება' : 'დამატება';
            $scope.object = {};
            $scope.object.gallery = [];
            if (item) {
                $scope.object = angular.copy(item);
                $scope.object.startDate = $scope.millisecondsToDate($scope.object.startDate);
                $scope.object.endDate = $scope.millisecondsToDate($scope.object.endDate);
            }
            $('#showAddEdit').modal('show');
        };

        $scope.save = function () {
            if($scope.uploader.queue.length > 0){
                uploader.uploadAll();
            }
            else {
                if($scope.object.gallery === undefined || $scope.object.gallery == null || $scope.object.gallery.length == 0){
                    $scope.showErrorModal("Please upload image.");
                    return
                }
                $scope.sendSave();
            }
        };

        $scope.sendSave = function () {
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
                    if(response.errorMessage === "RECORD_IS_USED_IN_OTHER_TABLES"){
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
            item.formData.push({type: 'TOUR_IMG'});
        };
        uploader.onCompleteItem = function(fileItem, response, status, headers) {
            if(response.success){
                if(response.source.length > 0){
                    $scope.object.gallery.push(response.source[0]);
                }
            }
        };
        uploader.onCompleteAll = function() {
            // Clear File Input
            $("input[type='file']").val('').clone(true);
            $scope.sendSave();
        };

        $('#showAddEdit').on("hidden.bs.modal", function(){
            uploader.queue = [];
            // Clear File Input
            $("input[type='file']").val('').clone(true);
        });


        $scope.millisecondsToDate = function (mills) {
            return new Date(mills).toLocaleDateString('ka-GE', {
                year: 'numeric',
                month: '2-digit',
                day: '2-digit'
            }).replace(/\./g, '/');
        }

    }]);
