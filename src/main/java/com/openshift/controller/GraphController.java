package com.openshift.controller;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openshift.service.FileService;
@RestController
@RequestMapping(path = "/chart")
public class GraphController {
	
	@Autowired
    private FileService service;

//	@RequestMapping("/plotgraph")
//	public String showNewProductPage(Model model,HttpSession session,HttpServletRequest request) throws IOException {
//		User user =new User();
//		user.setEmail(session.getAttribute("email").toString());	
//	    model.addAttribute("user",user);
//	 
//	 		}		
	 
    /**
     * Build a PNG image of a bar chart 
     *
     * @param width Width of the image.
     * @param height Height of the image.
     * @param response HTTP Response.
     * @throws IOException
     */
    @RequestMapping(path = "/kpabebar/{width}/{height}", method = RequestMethod.GET)
    public void buildKbAbeBarChart(@PathVariable("width") int width, @PathVariable("height") int height, HttpServletResponse response) throws IOException {
        final DefaultCategoryDataset categoryDataset = buildKpAbeTimeDataset();
        final String title = "Encryption time  of  KB-ABE";
        final String categoryAxisLabel = "Files";
        final String valueAxisLabel = "Time (nano sec)";
        final boolean legend = true;
        final boolean tooltips = true;
        final boolean urls = true;

        final JFreeChart barChart = ChartFactory.createBarChart(title, categoryAxisLabel, valueAxisLabel, categoryDataset, PlotOrientation.VERTICAL, legend, tooltips, urls);
        final CategoryPlot categoryPlot = (CategoryPlot) barChart.getPlot();
        final CategoryItemRenderer categoryItemRenderer = categoryPlot.getRenderer();
        categoryItemRenderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        categoryItemRenderer.setDefaultItemLabelsVisible(true);

        final ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
        categoryItemRenderer.setDefaultPositiveItemLabelPosition(position);

        writeChartAsPNGImage(barChart, width, height, response);
    }

    /**
     * Build a PNG image of a bar chart 
     *
     * @param width Width of the image.
     * @param height Height of the image.
     * @param response HTTP Response.
     * @throws IOException
     */
    @RequestMapping(path = "/mkpabebar/{width}/{height}", method = RequestMethod.GET)
    public void buildMKpAbeBarChart(@PathVariable("width") int width, @PathVariable("height") int height, HttpServletResponse response) throws IOException {
        final DefaultCategoryDataset categoryDataset = buildMKpAbeTimeDataset();
        final String title = "Encryption time  of Modified KB-ABE";
        final String categoryAxisLabel = "Files";
        final String valueAxisLabel = "Time (nano sec)";
        final boolean legend = true;
        final boolean tooltips = true;
        final boolean urls = true;

        final JFreeChart barChart = ChartFactory.createBarChart(title, categoryAxisLabel, valueAxisLabel, categoryDataset, PlotOrientation.VERTICAL, legend, tooltips, urls);
        final CategoryPlot categoryPlot = (CategoryPlot) barChart.getPlot();
        final CategoryItemRenderer categoryItemRenderer = categoryPlot.getRenderer();
        categoryItemRenderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        categoryItemRenderer.setDefaultItemLabelsVisible(true);

        final ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
        categoryItemRenderer.setDefaultPositiveItemLabelPosition(position);

        writeChartAsPNGImage(barChart, width, height, response);
    }
    private DefaultCategoryDataset buildKpAbeTimeDataset() {
        final Comparable<String> rowKey = "KP-ABE";
        final DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
        service.listAll().forEach(file->categoryDataset.setValue(file.getKpabetime(), rowKey,file.getName()));
        return categoryDataset;
    }
    private DefaultCategoryDataset buildMKpAbeTimeDataset() {
        final Comparable<String> rowKey = "Modified KP-ABE";
        final DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
        service.listAll().forEach(file->categoryDataset.setValue(file.getModifiedkpabetime(), rowKey,file.getName()));
        return categoryDataset;
    }
    /**
     * Build a PNG image of a line chart
     *
     * @param width Width of the image.
     * @param height Height of the image.
     * @param response HTTP Response.
     * @throws IOException
     */
    @RequestMapping(path = "/line/{width}/{height}", method = RequestMethod.GET)
    public void buildLineChart(@PathVariable("width") int width, @PathVariable("height") int height, HttpServletResponse response) throws IOException {
        final DefaultCategoryDataset categoryDataset = buildEncryptionTimeCategoryDataset();
        final String title = "Encryption time Comparison";
        final String categoryAxisLabel = "Files";
        final String valueAxisLabel = "Time (nano sec)";
        final boolean legend = true;
        final boolean tooltips = true;
        final boolean urls = true;

        final JFreeChart lineChart = ChartFactory.createLineChart(title, categoryAxisLabel, valueAxisLabel, categoryDataset, PlotOrientation.VERTICAL, legend, tooltips, urls);
        final CategoryPlot categoryPlot = (CategoryPlot) lineChart.getPlot();
        final CategoryItemRenderer categoryItemRenderer = categoryPlot.getRenderer();
        categoryItemRenderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        categoryItemRenderer.setDefaultItemLabelsVisible(true);

        final ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_LEFT);
        categoryItemRenderer.setDefaultPositiveItemLabelPosition(position);

        writeChartAsPNGImage(lineChart, width, height, response);
    }

    
    private DefaultCategoryDataset buildEncryptionTimeCategoryDataset() {
        final Comparable<String> rowKey1 = "M-KP-ABE";
        final Comparable<String> rowKey2 = "KP-ABE";
        final DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
        service.listAll().forEach(file->categoryDataset.setValue(file.getModifiedkpabetime(), rowKey1,file.getName()));
        service.listAll().forEach(file->categoryDataset.setValue(file.getKpabetime(), rowKey2,file.getName()));
        return categoryDataset;
    }

    private void writeChartAsPNGImage(final JFreeChart chart, final int width, final int height, HttpServletResponse response) throws IOException {
        final BufferedImage bufferedImage = chart.createBufferedImage(width, height);

        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        ChartUtils.writeBufferedImageAsPNG(response.getOutputStream(), bufferedImage);
    }
	
	
}
