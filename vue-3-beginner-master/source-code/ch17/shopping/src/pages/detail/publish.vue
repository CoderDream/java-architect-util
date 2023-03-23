<template>
  <div class="sales-board">
      <div class="sales-board-intro">
        <h2> 无线家用喷墨打印一体机</h2>
        <p>字体清晰，彩印的照片还原度高。复印，打印操作都很简单，按钮清晰易懂，符合用户的阿操作习惯。</p>
      </div>
      <div class="sales-board-form">
          <div class="sales-board-line">
              <div class="sales-board-line-left">
                  购买数量：
              </div>
              <div class="sales-board-line-right">
                  <v-counter :max="100" :min="1"></v-counter>
              </div>
          </div>
          <div class="sales-board-line">
              <div class="sales-board-line-left">
                  颜色：
              </div>
              <div class="sales-board-line-right">
                  <v-selection :selections="tradeList"></v-selection>
              </div>
          </div>
          <div class="sales-board-line">
              <div class="sales-board-line-left">
                  产品版本：
              </div>
              <div class="sales-board-line-right">
                  <v-mul-chooser :selections="versionList"></v-mul-chooser>
              </div>
          </div>
          <div class="sales-board-line">
              <div class="sales-board-line-left">
                  售后时间：
              </div>
              <div class="sales-board-line-right">
                  半年
              </div>
          </div>
          <div class="sales-board-line">
              <div class="sales-board-line-left">
                  总价：
              </div>
              <div class="sales-board-line-right">
                  2988 元
              </div>
          </div>
          <div class="sales-board-line">
              <div class="sales-board-line-left">&nbsp;</div>
              <div class="sales-board-line-right">
                  <div class="button">
                    立即购买
                  </div>
              </div>
          </div>
      </div>
      <div class="sales-board-des">
        <h2>产品介绍</h2>
        <p></p>

        <table class="sales-board-table">
          <tbody>
              <tr class="ui-table-row">
                  <td class="col-first">
                      <div class="intro-pic">
                          <label>用户反馈1</label>
                      </div>
                  </td>
                  <td>
                      <div class="intro-pic">
                          <label>用户反馈2</label>
                      </div>
                  </td>
                  <td>
                      <div class="intro-pic">
                          <label>用户反馈3</label>
                      </div>
                  </td>
                  <td>
                      <div class="intro-pic">
                          <label>用户反馈4</label>
                      </div>
                  </td>
              </tr>
          </tbody>
      </table>
      </div>
  </div>
</template>
<script>
import VSelection from '../../components/base/selection'
import VCounter from '../../components/base/counter'
import VMulChooser from '../../components/base/multiplyChooser'
export default {
  components: {
    VSelection,
    VCounter,
    VMulChooser
  },
  data () {
    return {
      tradeList: [
        {
          label: '灰色',
          value: 0
        },
        {
         label: '黑色',
          value:1
        } 
      ],
      versionList: [
        {
          label: '黑白版',
          value: 0
        },
        {
          label: '彩色版',
          value: 1
        },
        {
          label: '蓝牙版',
          value: 2
        }
      ]
    }
  }
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>

</style>
