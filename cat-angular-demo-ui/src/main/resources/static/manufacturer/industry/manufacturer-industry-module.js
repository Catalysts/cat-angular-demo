/**
 * Created by Mustafa on 06.08.2015.
 */
angular.module('cat.angular.demo.manufacturer.industry.ManufacturerIndustry', [])
    .config(function (catRouteServiceProvider) {
        catRouteServiceProvider
            .detailRoute('', 'Industry', {
                parent: 'Manufacturer.tab',
                name: 'ManufacturerIndustry',
                endpoint: {
                    parents: ['manufacturer'],
                    name: 'industry'
                }
            });
    });