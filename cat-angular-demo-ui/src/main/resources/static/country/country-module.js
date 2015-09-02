var app = angular.module('cat.demo.country.CountryModule', []);

// register the endpoint (countries)
app.config(function (catApiServiceProvider) {

    function Country(data) {
        angular.extend(this, data);     //maps all the data properties
    }

    catApiServiceProvider.endpoint('countries', {
        url: 'countries',
        model: Country
    });
});