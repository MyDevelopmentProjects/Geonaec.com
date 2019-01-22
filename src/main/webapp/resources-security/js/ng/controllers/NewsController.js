angular.module('app').controller('NewsController', ['$scope', '$http', 'FileUploader', 'GridManager',  function ($scope, $http, FileUploader, GridManager) {
    GridManager.givePowerTo($scope);

    angular.extend($scope, {
        url: 'news/list',
        saveURL: 'news/put',
        deleteURL: 'news/delete',
        init: {},
        options:{
            height: 300,
            toolbar: [
                ['edit',['undo','redo']],
                ['headline', ['style']],
                ['style', ['bold', 'italic', 'underline', 'superscript', 'subscript', 'strikethrough', 'clear']],
                ['fontface', ['fontname']],
                ['fontclr', ['color']],
                ['alignment', ['ul', 'ol', 'paragraph', 'lineheight']],
                ['height', ['height']],
                ['table', ['table']],
                ['insert', ['link','picture','video','hr']],
                ['view', ['fullscreen', 'codeview']],
                ['help', ['help']]
            ]
        },
        fileArr: [],
        uploadQuantity: 0
    });

    GridManager.givePowerTo($scope);
    $scope.AmfTable.openPage(0);

    $scope.showAddEdit = function (item) {
        $scope.init.action = item ? 'რედაქტირება' : 'დამატება';
        $scope.object = {};
        if (item) {
            $scope.object = angular.copy(item);
            if ($scope.object.files !== null && $scope.object.files !== "") {
                $scope.fileArr = JSON.parse($scope.object.files);
            }
        }
        $('#showAddEdit').modal('show');
        setTimeout(function () {
            $('#summernote').summernote({
                fontSizes: ['8', '9', '10', '11', '12', '14', '18', '24', '36', '48' , '64', '82', '150']
            });
        }, 1000)
    };

    $scope.sendSave = function () {
        var objectCopy = angular.copy($scope.object);
        objectCopy.files = JSON.stringify($scope.fileArr);
        $http.post($scope.saveURL, objectCopy).success(function (response) {
            if (!response.success) {
                $scope.showErrorModal("მოცემული ჩანაწერის წაშლა შეუძლებელია რადგან")
                return;
            }
            swal("Success", "თქვენი მოთხოვნა წარმატებით შესრულდა", "success");
            $scope.AmfTable.reloadData(true);
            $('#showAddEdit').modal('hide');
        });
    };

    $scope.delete = function (itemId) {
        $http.post($scope.deleteURL, itemId).success(function (data) {
            if (!data.success) {
                if (response.errorMessage == "RECORD_IS_USED_IN_OTHER_TABLES") {
                    $scope.showErrorModal("მოცემული ჩანაწერის წაშლა შეუძლებელია რადგან ის ფიქსირდება სხვა ცხრილშიც.")
                }
                return;
            }
            $scope.AmfTable.reloadData(true);
        });
    };

    $scope.removeItem = function (scope) {
        scope.remove();
    };

    $scope.toggleItem = function (scope) {
        scope.toggle();
    };

    $scope.moveLastToTheBeginning = function () {
        var a = $scope.data.pop();
        $scope.data.splice(0, 0, a);
    };

    $scope.newSubItem = function (scope) {
        var nodeData = scope.$modelValue;
        nodeData.nodes.push({
            id: nodeData.id * 10 + nodeData.nodes.length,
            title: nodeData.title + '.' + (nodeData.nodes.length + 1),
            nodes: []
        });
    };

    $scope.collapseAll = function () {
        $scope.$broadcast('angular-ui-tree:collapse-all');
    };

    $scope.expandAll = function () {
        $scope.$broadcast('angular-ui-tree:expand-all');
    };

    $scope.save = function () {
        if ($scope.uploader.queue.length === 0 && $scope.uploaderMultiple.queue.length === 0) {
            $scope.sendSave();
        } else {
            $scope.saveLazyMult();
            $scope.saveLazySingle();
        }
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
        if ($scope.uploadQuantity - 1 === 0) {
            $scope.sendSave();
        } else {
            $scope.uploadQuantity--
        }
    };


    var imageUploader = $scope.uploader = new FileUploader({
        url: 'upload/file'
    });

    imageUploader.filters.push({
        name: 'imageFilter',
        fn: function(item /*{File|FileLikeObject}*/, options) {
            var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
            return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
        }
    });

    imageUploader.onBeforeUploadItem = function(item) {
        item.formData.push({type: 'GENERAL'});
    };

    imageUploader.onCompleteItem = function(fileItem, response, status, headers) {
        if(response.success){
            if(response.source.length > 0){
                $scope.object.image = response.source[0];
            }
        }
    };

    imageUploader.onCompleteAll = function() {
        $(".uplSingle").val('').clone(true);
        if ($scope.uploadQuantity - 1 === 0) {
            $scope.sendSave();
        } else {
            $scope.uploadQuantity--
        }
    };

    $scope.saveLazyMult = function () {
        if($scope.uploaderMultiple.queue.length > 0){
            $scope.uploadQuantity++;
            imageUploaderMultiple.uploadAll();
        }
    };

    $scope.saveLazySingle = function () {
        if($scope.uploader.queue.length > 0){
            $scope.uploadQuantity++;
            imageUploader.uploadAll();
        }
    };

    $scope.removeFile = function(at) {
        $scope.fileArr.splice(at, 1);
    };

    $('#showAddEdit').on("hidden.bs.modal", function() {
        imageUploader.queue = [];
        imageUploaderMultiple.queue = [];
        $scope.uploadQuantity = 0;
        // Clear File Input
        $(".uplSingle").val('').clone(true);
        $(".uplMult").val('').clone(true);
    });


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
