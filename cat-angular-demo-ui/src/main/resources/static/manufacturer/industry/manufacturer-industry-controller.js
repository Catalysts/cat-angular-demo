/**
 * Created by Mustafa on 06.08.2015.
 */
angular.module('cat.angular.demo.manufacturer.industry.ManufacturerIndustryController', [])
    .controller('ManufacturerIndustryController', function ($scope, $stateParams) {
        $scope.manufacturerId = $stateParams.id;
    });