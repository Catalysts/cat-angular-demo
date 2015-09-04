(function () {
    'use strict';

    // depending on when this file is loaded, this may or may not be required
    window.cat = window.cat || {};
    window.cat.i18n = window.cat.i18n || {};
    window.cat.i18n.de = window.cat.i18n.de || {};
    window.cat.i18n.en = window.cat.i18n.en || {};

    _.assign(window.cat.i18n.de, {
        'cc.catalysts.general.new': 'Neu',
        'cc.catalysts.general.delete': 'Löschen',
        'cc.catalysts.general.edit': 'Bearbeiten',
        'cc.catalysts.general.cancel': 'Abbrechen',
        'cc.catalysts.general.save': 'Speichern',
        'cc.catalysts.cat-paginated.pagination.previous': 'Vorige',
        'cc.catalysts.cat-paginated.pagination.next': 'Nächste',
        'cc.catalysts.cat-paginated.pagination.first': 'Erste',
        'cc.catalysts.cat-paginated.pagination.last': 'Letzte'
    });

    _.assign(window.cat.i18n.en, {
        'cc.catalysts.general.new': 'New'
        //...
    });
})();