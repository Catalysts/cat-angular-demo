angular.module('cat.directives.pageSize', [
    'cat.directives.paginated'
])
    .directive('catPageSize', function CatCheckboxDirective() {
        return {
            replace: true,
            restrict: 'E',
            scope: {
                listData: '=?',
                // config { sizes : [..], defaultSize: 20 }
                config: '=?'
            },
            templateUrl: 'template/cat-page-size.tpl.html',
            controller: function ($scope) {

                // creates a default config, replaces settings supplied using the scope and reassigns the config to the scope.config object
                $scope.config = _.assign({sizes: [10, 20, 50, 100], defaultSize: 20}, $scope.config);

                if (_.isUndefined($scope.listData)) {
                    $scope.listData = $scope.$parent.listData;
                    if (_.isUndefined($scope.listData)) {
                        throw new Error('listData was not defined and couldn\'t be found with default value');
                    }
                }

                $scope.setSize = function (size) {
                    if (!!size) {
                        $scope.selectedSize = size;

                        if (!!$scope.listData.pagination && $scope.listData.pagination.size !== size) {
                            $scope.listData.pagination.size = size;
                        }
                    }
                };

                $scope.setSize($scope.config.defaultSize);

            },
            controllerAs: 'pageSizeCtrl'
        };
    });