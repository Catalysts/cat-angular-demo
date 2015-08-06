angular.module('cat.angular.demo.manufacturer.carmodel.ManufacturerCarmodelController', [])
    .controller('ManufacturerCarmodelController', function ($scope, $stateParams) {
        $scope.manufacturerId = $stateParams.id;
    });