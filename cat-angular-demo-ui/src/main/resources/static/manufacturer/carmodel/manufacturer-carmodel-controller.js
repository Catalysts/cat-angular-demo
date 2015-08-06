angular.module('cat.angular.demo.manufacturer.carmodel.ManufacturerCarmodelController', [
    'cat.angular.demo.manufacturer.carmodel.variation.ManufacturerCarmodelVariationController'
])
    .controller('ManufacturerCarmodelController', function ($scope, $stateParams) {
        $scope.manufacturerId = $stateParams.id;
    });