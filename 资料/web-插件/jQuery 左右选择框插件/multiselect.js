/* multiselect.js 左右框选择插件 */
;(function($){
    $.fn.multiselect = function(options){

        var opts = $.extend({}, $.fn.multiselect.defaults, options);

        this.fill = function () {
            var option = '';
            $.each(opts.data, function (key, val) {
                option += '<option data-id=' + val.id + '>' + val.text + '</option>';
            });
            $('.mul-src').append(option);
        };

        this.controll = function () {
            var mul = this;

            // $('.mul-src option').click(function(event) {
            //     $(this).css('');
            // });

            $('.mul-add').click(function(event) {
                var p = mul.find(".mul-src option:selected");
                p.clone().appendTo(mul.find(".mul-des"));
                p.remove();
            });

            // pickThis.find(".pAdd").on('click', function () {
            // var p = pickThis.find(".pickData option:selected");
            // p.clone().appendTo(pickThis.find(".pickListResult"));
            // p.remove();
            // });

            // pickThis.find(".pAddAll").on('click', function () {
            // var p = pickThis.find(".pickData option");
            // p.clone().appendTo(pickThis.find(".pickListResult"));
            // p.remove();
            // });

            // pickThis.find(".pRemove").on('click', function () {
            // var p = pickThis.find(".pickListResult option:selected");
            // p.clone().appendTo(pickThis.find(".pickData"));
            // p.remove();
            // });

            // pickThis.find(".pRemoveAll").on('click', function () {
            // var p = pickThis.find(".pickListResult option");
            // p.clone().appendTo(pickThis.find(".pickData"));
            // p.remove();
            // });
        };

        this.init = function () {
            var html =
                 "<div class='mul-rows'>" +
                 " <div class='mul-l'>" +
                 "   <select class='mul-selectd mul-src' multiple></select>" +
                 " </div>" +
                 " <div class='mul-btn'>" +
                 " <button class='mul-add btn btn-primary'>" + opts.add + "</button>" +
                 " <button  class='mul-addAll btn btn-primary'>" + opts.addAll + "</button>" +
                 " <button class='mul-remove btn btn-primary'>" + opts.remove + "</button>" +
                 " <button class='mul-removeAll btn btn-primary'>" + opts.removeAll + "</button>" +
                 " </div>" +
                 " <div class='mul-r'>" +
                 "    <select class='mul-selectd mul-des' multiple></select>" +
                 " </div>" +
                 "</div>";
            this.append(html);

            this.fill();
            this.controll();
        };

        this.init();
        return this;
    };

    $.fn.multiselect.defaults = {
      add: 'Add',
      addAll: 'Add All',
      remove: 'Remove',
      removeAll: 'Remove All'
   };
})(jQuery);