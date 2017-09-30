$(function () {
    var vm = new Vue({
        el: '#game-form',
        data: {
            properties: [],
            prices: [],
            name: '',
            advert: '',
            gamePriceId: 0,
            pageNum: 1,
            limit: 5,
            isEnd:false,
            count: 1
        },
        ready: function () {
            alert("asdf");
        },
        methods: {
            showData: function () {
                var _self = this;
                $.ajax({
                    type: 'GET',
                    url: '/front/gameProperty',
                    success: function (data) {
                        var json = eval('(' + data + ')');
                        _self.properties = json.gameProperty;
                        _self.name = json.gameName;
                        _self.prices = json.page.list;
                        _self.advert = json.advert;
                        _self.pageNum++;
                        _self.gamePriceId = _self.prices[0].id;
                    }
                });
            },
            add: function () {
                this.count++;
            },
            sub: function () {
                if (this.count > 1) {
                    this.count--;
                }
            },
            selectItem: function (price) {
                this.gamePriceId = price.id;
                $("#" + price.id).addClass('selected');
                $("#" + price.id).siblings('.charge-item').removeClass('selected');
            },
            loadMorePrice: function () {
                var _self = this;
                if (!_self.isEnd) {
                    $.ajax({
                        type: 'GET',
                        url: '/front/gamePrice?page=' + _self.pageNum + "&limit=" + _self.limit,
                        success: function (data) {
                            var json = eval('(' + data + ')');
                            _self.prices = _self.prices.concat(json.page.list);
                            if (json.page.list.length != 5) {
                                _self.isEnd = true;
                                return;
                            }
                            _self.pageNum++;
                        }
                    });
                }else{
                    alert("没有更多价位了。");
                }
            }
        }
    });
    vm.showData();
})
;


