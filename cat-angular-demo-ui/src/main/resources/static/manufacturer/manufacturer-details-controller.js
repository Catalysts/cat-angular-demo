angular.module('cat.demo.manufacturer.ManufacturerDetailsController', [
    'cat.angular.demo.manufacturer.carmodel.ManufacturerCarmodelController',
    'cat.angular.demo.manufacturer.industry.ManufacturerIndustryController'
])
.controller('ManufacturerDetailsController', function($scope){
        $scope.foundingDateModel = function(value) {
            if (angular.isDefined(value)) {
                $scope.editDetail.foundingDate = value.toISOString().substring(0,10);
            }

            return $scope.editDetail.foundingDate;
        }
    });