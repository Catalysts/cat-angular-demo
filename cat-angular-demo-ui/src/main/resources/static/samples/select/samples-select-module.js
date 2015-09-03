var app = angular.module('cat.angular.demo.samples.select.SelectModule', [
    'cat.demo.country.CountryModule'
]);


function SelectSamplesController($scope, catBreadcrumbsService) {

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
    $scope.staticArrayBasic = staticArray;

    // Sample: Array -> Function

    $scope.staticFunction = function (queryParams) {
        return queryParams.success({
            elements: staticArray
        });

    };

    // Sample: Array -> Filtered
    $scope.staticArrayFiltered = {
        endpoint: staticArray,
        filter: function (item, index, array) {
            return item.city;
        }
    };

};

app.controller('SamplesController', ['$scope', 'catBreadcrumbsService', SelectSamplesController]);