package com.hyperchain.niceweather.bean;

import java.util.List;

/**
 * Created by Newcon on 2016/11/26.
 */
public class WeatherJson {

    /**
     * aqi : {"city":{"aqi":"29","co":"1","no2":"57","o3":"8","pm10":"10","pm25":"8","qlty":"优","so2":"11"}}
     * basic : {"city":"宁波","cnty":"中国","id":"CN101210401","lat":"29.872000","lon":"121.542000","update":{"loc":"2016-11-26 13:51","utc":"2016-11-26 05:51"}}
     * daily_forecast : [{"astro":{"mr":"03:21","ms":"15:09","sr":"06:27","ss":"16:54"},"cond":{"code_d":"305","code_n":"104","txt_d":"小雨","txt_n":"阴"},"date":"2016-11-26","hum":"91","pcpn":"32.8","pop":"100","pres":"1023","tmp":{"max":"11","min":"6"},"uv":"1","vis":"9","wind":{"deg":"310","dir":"西北风","sc":"3-4","spd":"16"}},{"astro":{"mr":"04:12","ms":"15:44","sr":"06:28","ss":"16:54"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2016-11-27","hum":"74","pcpn":"0.0","pop":"0","pres":"1026","tmp":{"max":"13","min":"7"},"uv":"4","vis":"10","wind":{"deg":"328","dir":"北风","sc":"3-4","spd":"16"}},{"astro":{"mr":"05:04","ms":"16:20","sr":"06:29","ss":"16:54"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2016-11-28","hum":"77","pcpn":"0.1","pop":"1","pres":"1030","tmp":{"max":"14","min":"10"},"uv":"2","vis":"10","wind":{"deg":"10","dir":"东北风","sc":"3-4","spd":"14"}}]
     * hourly_forecast : [{"cond":{"code":"305","txt":"小雨"},"date":"2016-11-26 16:00","hum":"88","pop":"97","pres":"1022","tmp":"10","wind":{"deg":"309","dir":"西北风","sc":"微风","spd":"28"}},{"cond":{"code":"101","txt":"多云"},"date":"2016-11-26 19:00","hum":"88","pop":"36","pres":"1024","tmp":"9","wind":{"deg":"306","dir":"西北风","sc":"微风","spd":"25"}},{"cond":{"code":"104","txt":"阴"},"date":"2016-11-26 22:00","hum":"87","pop":"2","pres":"1024","tmp":"8","wind":{"deg":"307","dir":"西北风","sc":"3-4","spd":"22"}}]
     * now : {"cond":{"code":"300","txt":"阵雨"},"fl":"5","hum":"92","pcpn":"0.4","pres":"1022","tmp":"7","vis":"9","wind":{"deg":"320","dir":"西北风","sc":"6-7","spd":"35"}}
     * status : ok
     * suggestion : {"air":{"brf":"良","txt":"气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"},"comf":{"brf":"较舒适","txt":"白天会有降雨，这种天气条件下，人们会感到有些凉意，但大部分人完全可以接受。"},"cw":{"brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},"drsg":{"brf":"较冷","txt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"},"flu":{"brf":"较易发","txt":"天气较凉，较易发生感冒，请适当增加衣服。体质较弱的朋友尤其应该注意防护。"},"sport":{"brf":"较不宜","txt":"有降水，且风力较强，推荐您在室内进行低强度运动；若坚持户外运动，请注意保暖并携带雨具。"},"trav":{"brf":"适宜","txt":"有降水，虽然风稍大，但温度适宜，适宜旅游，可不要错过机会呦！"},"uv":{"brf":"最弱","txt":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"}}
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
         * city : {"aqi":"29","co":"1","no2":"57","o3":"8","pm10":"10","pm25":"8","qlty":"优","so2":"11"}
         */

        private AqiBean aqi;
        /**
         * city : 宁波
         * cnty : 中国
         * id : CN101210401
         * lat : 29.872000
         * lon : 121.542000
         * update : {"loc":"2016-11-26 13:51","utc":"2016-11-26 05:51"}
         */

        private BasicBean basic;
        /**
         * cond : {"code":"300","txt":"阵雨"}
         * fl : 5
         * hum : 92
         * pcpn : 0.4
         * pres : 1022
         * tmp : 7
         * vis : 9
         * wind : {"deg":"320","dir":"西北风","sc":"6-7","spd":"35"}
         */

        private NowBean now;
        private String status;
        /**
         * astro : {"mr":"03:21","ms":"15:09","sr":"06:27","ss":"16:54"}
         * cond : {"code_d":"305","code_n":"104","txt_d":"小雨","txt_n":"阴"}
         * date : 2016-11-26
         * hum : 91
         * pcpn : 32.8
         * pop : 100
         * pres : 1023
         * tmp : {"max":"11","min":"6"}
         * uv : 1
         * vis : 9
         * wind : {"deg":"310","dir":"西北风","sc":"3-4","spd":"16"}
         */

        private List<DailyForecastBean> daily_forecast;

        public AqiBean getAqi() {
            return aqi;
        }

        public void setAqi(AqiBean aqi) {
            this.aqi = aqi;
        }

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public NowBean getNow() {
            return now;
        }

        public void setNow(NowBean now) {
            this.now = now;
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

        public static class AqiBean {
            /**
             * aqi : 29
             * co : 1
             * no2 : 57
             * o3 : 8
             * pm10 : 10
             * pm25 : 8
             * qlty : 优
             * so2 : 11
             */

            private CityBean city;

            public CityBean getCity() {
                return city;
            }

            public void setCity(CityBean city) {
                this.city = city;
            }

            public static class CityBean {
                private String aqi;
                private String pm25;
                private String qlty;

                public String getAqi() {
                    return aqi;
                }

                public void setAqi(String aqi) {
                    this.aqi = aqi;
                }

                public String getPm25() {     //需要使用
                    return pm25;
                }

                public void setPm25(String pm25) {
                    this.pm25 = pm25;
                }

                public String getQlty() {     //需要使用
                    return qlty;
                }

                public void setQlty(String qlty) {
                    this.qlty = qlty;
                }
            }
        }

        public static class BasicBean {
            private String city;
            private String cnty;

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
        }

        public static class NowBean {
            /**
             * code : 300
             * txt : 阵雨
             */

            private CondBean cond;
            private String fl;
            private String hum;
            private String pcpn;
            private String pres;
            private String tmp;
            private String vis;
            /**
             * deg : 320
             * dir : 西北风
             * sc : 6-7
             * spd : 35
             */

            private WindBean wind;

            public CondBean getCond() {
                return cond;
            }

            public void setCond(CondBean cond) {
                this.cond = cond;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
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

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {             //需要使用
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
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
                private String code;
                private String txt;

                public String getCode() {      //需要使用   晴，阴，多云
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
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

        public static class DailyForecastBean {
            /**
             * mr : 03:21
             * ms : 15:09
             * sr : 06:27
             * ss : 16:54
             */

            private AstroBean astro;
            /**
             * code_d : 305
             * code_n : 104
             * txt_d : 小雨
             * txt_n : 阴
             */

            private CondBean cond;
            private String date;
            private String hum;
            private String pcpn;
            private String pop;
            private String pres;
            /**
             * max : 11
             * min : 6
             */

            private TmpBean tmp;
            private String uv;
            private String vis;
            /**
             * deg : 310
             * dir : 西北风
             * sc : 3-4
             * spd : 16
             */

            private WindBean wind;

            public AstroBean getAstro() {
                return astro;
            }

            public void setAstro(AstroBean astro) {
                this.astro = astro;
            }

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

            public static class AstroBean {
                private String mr;
                private String ms;
                private String sr;
                private String ss;

                public String getMr() {
                    return mr;
                }

                public void setMr(String mr) {
                    this.mr = mr;
                }

                public String getMs() {
                    return ms;
                }

                public void setMs(String ms) {
                    this.ms = ms;
                }

                public String getSr() {
                    return sr;
                }

                public void setSr(String sr) {
                    this.sr = sr;
                }

                public String getSs() {
                    return ss;
                }

                public void setSs(String ss) {
                    this.ss = ss;
                }
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

                public String getMax() {         //最高气温
                    return max;
                }

                public void setMax(String max) {
                    this.max = max;
                }

                public String getMin() {          //最低气温
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
