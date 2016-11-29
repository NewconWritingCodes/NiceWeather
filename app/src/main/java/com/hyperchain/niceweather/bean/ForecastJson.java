package com.hyperchain.niceweather.bean;

import java.util.List;

/**
 * Created by Newcon on 2016/11/28.
 */
public class ForecastJson {

    /**
     * basic : {"city":"宁波","cnty":"中国","id":"CN101210401","lat":"29.872000","lon":"121.542000","update":{"loc":"2016-11-28 14:51","utc":"2016-11-28 06:51"}}
     * daily_forecast : [{"astro":{"mr":"05:04","ms":"16:20","sr":"06:29","ss":"16:54"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2016-11-28","hum":"75","pcpn":"0.0","pop":"0","pres":"1030","tmp":{"max":"14","min":"8"},"uv":"4","vis":"10","wind":{"deg":"8","dir":"北风","sc":"3-4","spd":"14"}},{"astro":{"mr":"05:56","ms":"17:00","sr":"06:30","ss":"16:54"},"cond":{"code_d":"101","code_n":"104","txt_d":"多云","txt_n":"阴"},"date":"2016-11-29","hum":"81","pcpn":"0.2","pop":"11","pres":"1031","tmp":{"max":"15","min":"9"},"uv":"2","vis":"10","wind":{"deg":"66","dir":"东北风","sc":"微风","spd":"0"}},{"astro":{"mr":"06:48","ms":"17:43","sr":"06:31","ss":"16:54"},"cond":{"code_d":"104","code_n":"101","txt_d":"阴","txt_n":"多云"},"date":"2016-11-30","hum":"82","pcpn":"0.3","pop":"100","pres":"1028","tmp":{"max":"14","min":"7"},"uv":"3","vis":"10","wind":{"deg":"340","dir":"西北风","sc":"3-4","spd":"13"}}]
     * status : ok
     */

    private List<HeWeather5Bean> HeWeather5;

    public List<HeWeather5Bean> getHeWeather5() {
        return HeWeather5;
    }

    public void setHeWeather5(List<HeWeather5Bean> HeWeather5) {
        this.HeWeather5 = HeWeather5;
    }

    public static class HeWeather5Bean {
        /**
         * city : 宁波
         * cnty : 中国
         * id : CN101210401
         * lat : 29.872000
         * lon : 121.542000
         * update : {"loc":"2016-11-28 14:51","utc":"2016-11-28 06:51"}
         */

        private BasicBean basic;
        private String status;
        /**
         * astro : {"mr":"05:04","ms":"16:20","sr":"06:29","ss":"16:54"}
         * cond : {"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"}
         * date : 2016-11-28
         * hum : 75
         * pcpn : 0.0
         * pop : 0
         * pres : 1030
         * tmp : {"max":"14","min":"8"}
         * uv : 4
         * vis : 10
         * wind : {"deg":"8","dir":"北风","sc":"3-4","spd":"14"}
         */

        private List<DailyForecastBean> daily_forecast;

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<DailyForecastBean> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public static class BasicBean {
            private String city;
            private String cnty;
            /**
             * loc : 2016-11-28 14:51
             * utc : 2016-11-28 06:51
             */

            private UpdateBean update;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public UpdateBean getUpdate() {
                return update;
            }

            public void setUpdate(UpdateBean update) {
                this.update = update;
            }

            public static class UpdateBean {
                private String loc;
                private String utc;

                public String getLoc() {
                    return loc;
                }

                public void setLoc(String loc) {
                    this.loc = loc;
                }

                public String getUtc() {
                    return utc;
                }

                public void setUtc(String utc) {
                    this.utc = utc;
                }
            }
        }

        public static class DailyForecastBean {
            /**
             * code_d : 101
             * code_n : 101
             * txt_d : 多云
             * txt_n : 多云
             */

            private CondBean cond;
            private String date;
            private String hum;
            private String pcpn;
            private String pop;
            private String pres;
            /**
             * max : 14
             * min : 8
             */

            private TmpBean tmp;
            private String uv;
            private String vis;
            /**
             * deg : 8
             * dir : 北风
             * sc : 3-4
             * spd : 14
             */

            private WindBean wind;

            public CondBean getCond() {
                return cond;
            }

            public void setCond(CondBean cond) {
                this.cond = cond;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public TmpBean getTmp() {
                return tmp;
            }

            public void setTmp(TmpBean tmp) {
                this.tmp = tmp;
            }

            public String getUv() {
                return uv;
            }

            public void setUv(String uv) {
                this.uv = uv;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public static class CondBean {
                private String code_d;
                private String code_n;
                private String txt_d;
                private String txt_n;

                public String getCode_d() {
                    return code_d;
                }

                public void setCode_d(String code_d) {
                    this.code_d = code_d;
                }

                public String getCode_n() {
                    return code_n;
                }

                public void setCode_n(String code_n) {
                    this.code_n = code_n;
                }

                public String getTxt_d() {
                    return txt_d;
                }

                public void setTxt_d(String txt_d) {
                    this.txt_d = txt_d;
                }

                public String getTxt_n() {
                    return txt_n;
                }

                public void setTxt_n(String txt_n) {
                    this.txt_n = txt_n;
                }
            }

            public static class TmpBean {
                private String max;
                private String min;

                public String getMax() {
                    return max;
                }

                public void setMax(String max) {
                    this.max = max;
                }

                public String getMin() {
                    return min;
                }

                public void setMin(String min) {
                    this.min = min;
                }
            }

            public static class WindBean {
                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }
    }
}
