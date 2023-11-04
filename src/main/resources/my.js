$('form').eq(0).on("change", function(e){
    $('input[name^=field]').each(function()
    {
      if(e.target != this)
        this.checked = false;
    });
})

$('form').eq(0).on('submit', function() {
     return $('input[name^=field]:checked:enabled').length == 1;
});