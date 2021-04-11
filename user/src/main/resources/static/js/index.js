
layui.use(['layer', 'element'], function() {
    var layer = layui.layer;
    var element = layui.element;

    // 监听左侧导航
    element.on('nav(left_nav)', function(elem) {
        var url = elem.attr('data-url');
        var id = elem.attr('data-id');
        var test = elem.attr('data-text');

        var isActive = $('.main-layout-tab .layui-tab-title').find("li[lay-id=" + id + "]");
        if (isActive.length > 0) {
            element.tabChange('tab', id);
        } else {
            element.tabAdd('tab', {
                title : test,
                content : '<iframe src="' + url + '" name="iframe' + id + '" class="iframe" framborder="0" data-id="' + id + '" scrolling="auto" width="100%"  height="100%"></iframe>',
                id : id
            });
            element.tabChange('tab', id);
        }
    })
})