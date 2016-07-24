jQuery(function($) {
    function responsiveTable(tableSelector) {
        var table = $(tableSelector);
        var allHighlightableCells = table.find('td.highlightable');
        var firstColumnCells = table.find('td:first-child');
        table.find('td.cell').click(function(e) {
            allHighlightableCells.removeClass('highlighted');
            firstColumnCells.removeClass('dependency');
            table.find('.relative-dependency').text('');
            var clickedElem = $(e.target);
            var td = clickedElem.is('td') ? clickedElem : clickedElem.parent('td');
            var clickedColumn = td.index() + 1;
            var clickedRow = td.parent().index() + 1;
            var cellsInRowOrColumn =
                'td.highlightable:nth-child(' + clickedColumn + '),tr:nth-child(' + clickedRow + ')>td.highlightable';
            table.find(cellsInRowOrColumn).addClass('highlighted');
            var depenencyMethodInFirstColumn = 'tr:nth-child(' + clickedColumn + ')>td:first-child';
            table.find(depenencyMethodInFirstColumn).addClass('dependency');
            var dependenciesList = eval(td.parent().children().first().data('dependencies-indices'));
            dependenciesList.forEach(function(dependency, index) {
                var selector = 'tr:nth-child(' + (dependency + 2) + ')>td:first-child .relative-dependency';
                table.find(selector).text('('+ (index + 1) + ')');
            });
        });
    }

    function setUpTableDimensions(tableSelector) {
        var table = $(tableSelector);
        var maxWidth = table.find('.horizontal-heading td')
            .get()
            .map(function(elem) {
                return $(elem).width();
            })
            .sort(function(l, r) {
                return parseInt(l) - parseInt(r);
            })
            .pop();
        table.find('.horizontal-heading td').css('maxWidth', '35px');
        table.find('tr.horizontal-heading>td').css('height', maxWidth);
    }

    var tableSelector = '#matrixTable';
    setUpTableDimensions(tableSelector);
    responsiveTable(tableSelector);
});
