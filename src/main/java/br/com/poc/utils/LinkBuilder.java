package br.com.poc.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.jboss.jbossts.star.util.TxLinkNames;

public class LinkBuilder {
	
	public List<Link> parse(final List<String> links) {
		return links.stream().map(l -> new Link(parseURL(l), parseRel(l), parseTitle(l))).collect(Collectors.toList());
	}
	
	private String parseURL(final String link) {
		return StringUtils.substringBetween(link, "<", ">");
	}
	
	private String parseRel(final String link) {
		return StringUtils.substringBetween(link, "rel=\"", "\"");
	}
	
	private String parseTitle(final String link) {
		return StringUtils.substringBetween(link, "title=\"", "\"");
	}
	
	public static List<Link> fromUri(final String baseURI, final int workId) {
		final String prefixUrl = baseURI + "/" + workId + "/";
		final Link participant = new Link(prefixUrl + TxLinkNames.PARTICIPANT_RESOURCE, TxLinkNames.PARTICIPANT_RESOURCE, null);
		final Link terminator = new Link(prefixUrl + TxLinkNames.PARTICIPANT_TERMINATOR, TxLinkNames.PARTICIPANT_TERMINATOR, null);
		
		return Arrays.asList(participant, terminator);
	}
	
	public static String combineLinks(final List<Link> links) {
		return StringUtils.join(links, ",");
	}
	
	public static List<Link> build(final List<String> links) {
		return new LinkBuilder().parse(new ArrayList<>(links));
	}
}