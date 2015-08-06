/**
 * Created by Mustafa on 06.08.2015.
 */
angular.module('cat.angular.demo.manufacturer.carmodel.variation.ManufacturerCarmodelVariation', [])
    .config(function (catRouteServiceProvider) {
        catRouteServiceProvider
            .detailRoute('', 'Variation', {
                parent: 'CarModel.tab',
                name: 'CarModelVariation',
                endpoint: {
                    parents: ['carModel'],
                    name: 'variation'
                }
            });
    });