var HtmlWebpackPlugin = require('html-webpack-plugin');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var CleanWebpackPlugin = require('clean-webpack-plugin');
var BowerWebpackPlugin = require('bower-webpack-plugin');

var webpack = require('webpack');

module.exports = {
	entry: {
		app: './app.js'
	},
	output: {
		path: './build',
		filename: 'app.js',
		publicPath: './resources'
	},
	plugins: [
		new HtmlWebpackPlugin({
			template: 'index.html',
			inject: true
		}),
		new HtmlWebpackPlugin({
			template: 'login.html',
			inject: true,
			filename: 'login.html'
		}),
		new ExtractTextPlugin('style.[contenthash:6].css'),
		new CleanWebpackPlugin(['build'], {
			verbose: true
		}),
		new BowerWebpackPlugin({
			modulesDirectories: ['bower_components'],
			manifestFiles: "bower.json",
			includes: /.*/,
			excludes: []
		}),
		new webpack.optimize.UglifyJsPlugin()
	],
	module: {
		loaders: [{
			test: /\.html$/,
			loader: 'html'
		}, {
			test: /\.css$/,
			loader: ExtractTextPlugin.extract('css')
		}, {
			test: /\.png$/,
			loader: 'file?name=[name].[hash:6].[ext]'
		}],
		preLoaders: [{
			test: /\.js$/,
			loader: "eslint-loader",
			exclude: /node_modules|bower_components/
		}]
	},

	devtool: 'source-map'
};