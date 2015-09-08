(function () {
    var app = angular.module('cat.angular.demo.samples.select.Controller', []);

    function SamplesSelectController($scope, catBreadcrumbsService) {

        catBreadcrumbsService.clear();

        catBreadcrumbsService.push({
            title: 'cat-select',
            key: 'cc.catalysts.samples.catselect'
        });

        var staticArray = [{id: 1, name: 'Wien', city: true}, {id: 2, name: 'Linz', city: true}, {
            id: 3,
            name: 'Hinterdupfing',
            city: false
        }];


        // Sample: Array -> Basic
        // Array as endpoint.
        $scope.staticArrayBasic = staticArray;

        // Sample: Array -> Function
        // Function as endpoint.
        $scope.staticFunction = function (queryParams) {
            return queryParams.success({
                elements: staticArray
            });
        };

        // Sample: Array -> Filtered
        // Setting a custom filter function.
        $scope.staticArrayFiltered = {
            endpoint: staticArray,
            filter: function (item, index, array) {
                return item.city;
            }
        };

        // Sample: Custom formatting function
        $scope.formatFunction = {
            endpoint: staticArray,
            'ui-select2': {
                // the text used for the select list items
                formatResult: function (obj) {
                    return obj.name + (obj.city ? ' (City)' : ' (Town)');
                },
                // the text used for the selected item
                formatSelection: function (obj) {
                    return obj.name;
                }
            }
        };


        //Sample: Pre selected item
        $scope.selectedItemPreSelected = staticArray[1];
        $scope.preSelectedConfig = {
            endpoint: staticArray
        };


        // Sample: Infinite Scrolling
        var pagingSource = [];

        for (var i = 0; i < 1000; i++) {
            pagingSource.push({id: i, name: 'Nr.: ' + i});
        }
        var pagingSourceChunks = _.chunk(pagingSource, 100);

        $scope.pagingSource = function (queryParams) {

            var page = queryParams.data.page - 1;
            var elements = pagingSourceChunks[page];

            return queryParams.success({
                elements: elements,
                totalCount: pagingSource.length
            });
        };


    }

    app.controller('SamplesSelectController', ['$scope', 'catBreadcrumbsService', SamplesSelectController]);
})();