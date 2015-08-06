/**
 * Created by Mustafa on 06.08.2015.
 */
angular.module('cat.angular.demo.manufacturer.carmodel.variation.ManufacturerCarmodelVariationController', [])
    .controller('ManufacturerCarmodelVariationController', function ($scope, $stateParams) {
        $scope.carmodelId = $stateParams.id;
    });