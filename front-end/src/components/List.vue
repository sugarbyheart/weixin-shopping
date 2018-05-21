<template>
  <div class="weui-tab" id="tab">
    <div class="weui-navbar">

      <template v-if="type === 'xinluo'">
        <div class="weui-navbar__item weui-bar__item_on" v-on:click="getList('xinluo')">新罗</div>
      </template>
      <template v-else>
        <div class="weui-navbar__item" v-on:click="getList('xinluo')">新罗</div>
      </template>
      <template v-if="type === 'letian'">
        <div class="weui-navbar__item weui-bar__item_on" v-on:click="getList('letian')">乐天</div>
      </template>
      <template v-else>
        <div class="weui-navbar__item" v-on:click="getList('letian')">乐天</div>
      </template>

      <!--<div class="weui-navbar__item">上传</div>-->
      <!--<div class="weui-navbar__item">其它</div>-->
    </div>

    <div class="weui-tab__panel">
      <div class="weui-tab_content page_feedback" style="display:block;">
        <div class="weui-search-bar" id="searchBar">
          <form class="weui-search-bar__form">
            <div class="weui-search-bar__box">
              <i class="weui-icon-search"></i>
              <input type="search" id = "search_text" class="weui-search-bar__input" placeholder="搜索" required="" v-on:keyup="enterWord()">
              <a href="javascript:" class="weui-icon-clear"></a>
            </div>
            <label class="weui-search-bar__label">
              <i class="weui-icon-search"></i>
              <span>搜索</span>
            </label>
          </form>
          <a href="javascript:" class="weui-search-bar__cancel-btn">取消</a>
        </div>
      </div>
    </div>
    <br>


    <div class="page preview js_show">
      <div class="page__hd">
        <!--<h1 class="page__title">Preview</h1>-->
        <!--<p class="page__desc">表单预览</p>-->
      </div>
      <div class="page__bd" style="height: 100%;">
        <div class="weui-form-preview" v-for="(item, index) in items">
          <div class="weui-form-preview__hd">
            <div class="weui-form-preview__item">
              <label class="weui-form-preview__label">{{ item.broker_id }}</label>
              <!--<em class="weui-form-preview__value">¥2400.00</em>-->
            </div>
          </div>
          <div class="weui-form-preview__bd" >
            <a v-bind:href="link2" class="weui-media-box weui-media-box_appmsg">
              <div class="weui-media-box__hd">
                <img class="weui-media-box__thumb"
                     src="http://image2.shilladfs.com/files/product/2018/02/12/030710000195_20180212_11165951_12_030710000195_7.JPG">
              </div>
              <div class="weui-media-box__bd">
                <!--<h4 class="weui-media-box__title">标题一</h4>-->

                <p class="weui-media-box__desc">{{ item.model_name }}</p>

              </div>
            </a>
          </div>
          <div class="weui-form-preview__ft">
            <a class="weui-form-preview__btn weui-form-preview__btn_primary" href="javascript:"
               v-on:click="deleteItem(item, index)">delete {{ index }}</a>
            <!--<a class="weui-form-preview__btn weui-form-preview__btn_primary" href="javascript:"> {{ counter }} </a>-->
          </div>
          <br>
        </div>

      </div>

      <div class="page__ft">

      </div>
    </div>


  </div>


</template>


<script>
  weui.searchBar('#searchBar');

  export default {
    name: 'HelloWorld',

    beforeMount: function () {
      this.getList('xinluo');
//      this.getList('letian');
    },

    data () {
      return {
        link1: 'http://www.baidu.com',
        link2: 'http://www.sina.com',
        counter: 0,
        items: [],
        items_bak: [],
        size_xinluo: '',
        size_letian: '',
        type: 'xinluo'
      }
    },

    methods: {

      deleteItem: function (item, index) {
        var self = this;
        $.ajax({
          url: 'http://121.40.90.141:8003/broker/overview/royaltyinfo/brokerid/B01203',
          method: 'GET',
          success: function (obj) {
            console.log(self.items);
            console.log(self.items_bak);
            self.items.splice(index, 1);
            self.items_bak.splice(index, 1);
            console.log(self.items);
            console.log(self.items_bak);
          },
          error: function (error) {
            console.log(error);
          }
        });
      },
      enterWord: function() {
        console.log(this.items_bak);
        var text = $('#search_text').val()
        if (!text) {
          this.items = this.items_bak.slice(0);
          return;
        }
        var new_items = []
        for (var index in this.items_bak) {
          if (this.items_bak[index].broker_id.indexOf(text) !== -1 || this.items_bak[index].model_name.indexOf(text) !== -1) {
            new_items.push(this.items_bak[index]);
          }
        }
        this.items = new_items;
      },
      getList: function (website) {
        var self = this;
        var link = '';
        if (website == 'xinluo') {
          link = 'http://121.40.90.141:8003/broker/overview/royaltyinfo/brokerid/B01552';
          self.type = 'xinluo';
        } else if (website == 'letian') {
          link = 'http://121.40.90.141:8003/broker/overview/royaltyinfo/brokerid/B01203';
          self.type = 'letian';
        }
        $.ajax({
          url: link,
          method: 'GET',
          success: function (obj) {
            self.items_bak = obj.data.slice(0);
            self.items = obj.data.slice(0);
            if (website == 'xinluo') {
              self.size_xinluo = obj.data.length;
            } else if (website == 'letian') {
              self.size_letian = obj.data.length;
            }
            console.log(obj.data);
          },
          error: function (error) {
            console.log(error);
          }
        });
      }
    }


  }
</script>
