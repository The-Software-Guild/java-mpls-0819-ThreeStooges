function toggleBrand(brandName) {
    
    if(params.brand == brandName) {
        delete params.brand
    } else {
        params.brand = brandName;
    }
    runSearch(params);
}

function toggleColor(colorName) {
    if(params.color == colorName) {
        delete params.color
    } else {
        params.color = colorName;
    }
    runSearch(params);
}

function toggleSize(size) {
    if(params.size == size) {
        delete params.size
    } else {
        params.size = size;
    }
    runSearch(params);
}

function runSearch(params) {
    var searchString = "?";
    $.each(params, function(key, value) {
        searchString += key + "=" + value + "&";
    });
    window.location.href = searchString;
}
