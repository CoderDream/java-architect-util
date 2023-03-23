<template>
  <div class="sales-board">
      <div class="sales-board-intro">
        <h2>智能手表</h2>
        <p>运动脉搏血氧跑步游泳触屏全新心率传感器肌肉热力图时尚腕表</p>
      </div>
      <div class="sales-board-form">
          <div class="sales-board-line">
              <div class="sales-board-line-left">
                  产品类型：
              </div>
              <div class="sales-board-line-right">
                  <v-chooser :selections="buyTypes"></v-chooser>
              </div>
          </div>
          <div class="sales-board-line">
              <div class="sales-board-line-left">
                  适用人群：
              </div>
              <div class="sales-board-line-right">
                  <v-selection :selections="districts"></v-selection>
              </div>
          </div>
          <div class="sales-board-line">
              <div class="sales-board-line-left">
                  售后时间：
              </div>
              <div class="sales-board-line-right">
                  12个月
              </div>
          </div>
          <div class="sales-board-line">
              <div class="sales-board-line-left">
                  总价：
              </div>
              <div class="sales-board-line-right">
                 360 元
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
        <h2>产品说明</h2>
        <p>运动脉搏血氧跑步游泳触屏全新心率传感器肌肉热力图时尚腕表</p>

        <table class="sales-board-table">
          <tbody>
              <tr class="ui-table-row">
                  <td class="col-first">
                      <div class="intro-pic">
                          <label>智能手表除指示时间之外，还应具有提醒、导航、校准、监测、交互等其中一种或者多种功能；显示方式包括指针、数字、图像等。</label>
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
import VChooser from '../../components/base/chooser'
export default {
  components: {
    VChooser,
    VSelection
  },
  data () {
    return {
      buyTypes: [
        {
          label: '白色版',
          value: 0
        },
        {
          label: '绿色版',
          value: 1
        },
        {
          label: '紫色版',
          value: 2
        }
      ],
      districts: [
        {
          label: '年轻人',
          value: 0
        },
        {
          label: '老年人',
          value: 1
        },
      ]
    }
  }
}
</script>
Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
